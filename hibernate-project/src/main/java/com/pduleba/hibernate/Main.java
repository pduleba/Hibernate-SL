package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.persistence.LockModeType;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.internal.util.LockModeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.controller.SpringController;
import com.pduleba.spring.services.WorkerService;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private SpringController controller;
	private WorkerService worker;

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
	}

	private void configureLogger() {
		final String LOG4J_CLASSPATH_LOCATION = "/config/log4j.properties";
		ClassPathResource log4jResource = new ClassPathResource(LOG4J_CLASSPATH_LOCATION);
		try {
			PropertyConfigurator.configure(log4jResource.getURL());
		} catch (IOException e) {
			throw new InvalidParameterException(MessageFormat.format("Argument ''{0}'' not found!", LOG4J_CLASSPATH_LOCATION));
		}
	}
	
	private void run() {
		configureLogger();
		
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			this.worker = ctx.getBean(WorkerService.class);
			
			LOG.info("##### Starting... #####");
			start();
		}
	}
	
	private void start() {
		final int[] TIMEOUTS = {0, 1000, 2000, 3000, 4000};
		final int THREADS_NUMBER = TIMEOUTS.length;
		// synchronizes all threads to wait each other to reach start point
		final CyclicBarrier startPoint = new CyclicBarrier(THREADS_NUMBER);
		// synchronizes all threads to be ready to start
		final CountDownLatch readyToGo = new CountDownLatch(THREADS_NUMBER);
		// synchronizes all threads to start at same time
		final CountDownLatch readySetGoFlag = new CountDownLatch(5);
		
		JPAThread thread = null;
		String threadName;
		List<JPAThread> threads = new LinkedList<>();
		CarModel persisted = create();
		long carId = persisted.getId();
		
		for (int i = 0; i < TIMEOUTS.length; i++) {
			threadName = MessageFormat.format("thread-{0}", i);
			thread = new JPAThread(threadName, TIMEOUTS[i], carId, readySetGoFlag, readyToGo, startPoint);
			thread.start();
			threads.add(thread);
		}

		try {
			readyToGo.await();
		} catch (InterruptedException e) {
			LOG.error("Error", e);
			return;
		}
		
		lock(persisted, LockMode.OPTIMISTIC);
		update(persisted, Thread.currentThread().getName());

		while (readySetGoFlag.getCount() > 0) {
			LOG.info("{}", readySetGoFlag.getCount());
			readySetGoFlag.countDown();
			sleep(1000);
		}

		try {
			for (JPAThread t : threads) {
				t.join();
			}
			threads.clear();
		} catch (InterruptedException e) {
			LOG.error("Unable to join", e);
			return;
		}
		
		LOG.info("Complete");
	}

	private void sleep(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			LOG.error("Error on sleep", e);
		}
	}
	
	private CarModel create() {
		CarModel car = worker.getCar();
		
		LOG.info(" ----- CREATE ----- ");
		this.controller.create(car);
		
		return car;
	}

	private CarModel read(long carId) {
		LOG.info(" ----- READ ----- ");
		CarModel car = this.controller.read(carId);
		showCar(car, WorkerService.Mode.AFTER__READ);

		return car;
	}

	private void update(final CarModel car, String newName) {
		LOG.info(" ----- UPDATE ----- ");
		car.setName(newName);
		
		showCar(car, WorkerService.Mode.BEFORE_UPDATE);
		try {
			this.controller.update(car);
			showCar(car, WorkerService.Mode.AFTER__UPDATE);
		} catch (Exception e) {
			LOG.error("Optymistic Lock Exception :: lock found");
		}
	}

//	private void delete(CarModel car) {
//		LOG.info(" ----- DELETE ----- ");
//		this.controller.delete(car);
//		this.worker.showCar(car, Mode.DELETE);
//	}

	private void lock(final CarModel car, LockMode lockMode) {
		this.controller.lock(car, lockMode);
	}
	
	private void lock(final CarModel car, LockModeType lockMode) {
		lock(car, LockModeConverter.convertToLockMode(lockMode));
	}
	
	public void showCar(CarModel car, WorkerService.Mode mode) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(car))) {
			LOG.info("{} :: NOT INITIALIZED", mode);
		} else if (Objects.isNull(car)) {
			LOG.info("{} :: NOT FOUND", mode);
		} else {
			LOG.info("{} :: {}", mode, car);
		}
	}
	
	class JPAThread extends Thread {

		private int waitTime;
		private long carId;
		private CyclicBarrier startPoint;
		private CountDownLatch readyToGo;
		private CountDownLatch readySteadyGo;

		public JPAThread(String name, int waitTime, long carId, CountDownLatch readySteadyGo,
				CountDownLatch readyToGo, CyclicBarrier startPoint) {
			super(name);
			this.waitTime = waitTime;
			this.carId = carId;
			this.startPoint = startPoint;
			this.readyToGo = readyToGo;
			this.readySteadyGo = readySteadyGo;
		}

		@Override
		public void run() {

			try {
				LOG.info("Waiting for other childs on start point");
				startPoint.await();
			} catch (Exception e) {
				LOG.warn("Unable to wait for other childs on start point");
				return;
			}
			
			CarModel persisted = read(carId);
			readyToGo.countDown();
			
			try {
				LOG.info("Waiting for main thread to let me start");
				readySteadyGo.await();
			} catch (Exception e) {
				LOG.warn("Unable to wait for main thread to let me start");
				return;
			}
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				LOG.warn("Unable to wait for a duration of {} ms", waitTime);
				return;
			}
			
			LOG.info("Updating persisted object");
			update(persisted, getName());
		}
	}
}


