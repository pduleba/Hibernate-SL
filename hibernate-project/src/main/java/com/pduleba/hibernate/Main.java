package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

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
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

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
		final int THREADS_NUMBER = 2;
		final int LONG_RUNNING_ACTION_SLEEP_TIME = 400;
		final int LONG_RUNNING_ACTION_TIME = 400;
		
		// synchronizes all threads to be ready to start
		final CountDownLatch readyFlag = new CountDownLatch(THREADS_NUMBER);
		// synchronizes all threads to start at same time
		final CountDownLatch startFlag = new CountDownLatch(1);
		// synchronizes all threads complete
		final CountDownLatch completeFlag = new CountDownLatch(THREADS_NUMBER);
		
		JPAThread thread = null;
		String threadName;
		List<JPAThread> threads = new LinkedList<>();
		CarModel persisted = create();
		long carId = persisted.getId();
		
		for (int i = 0; i < THREADS_NUMBER; i++) {
			threadName = MessageFormat.format("thread-{0}", i);
			thread = new JPAThread(threadName, carId, readyFlag, startFlag, completeFlag);
			thread.start();
			threads.add(thread);
		}

		await(readyFlag);

		// lock entity so children will will not be able to update it 
		lock(persisted, LockMode.PESSIMISTIC_WRITE);
		startFlag.countDown();
		
		await(completeFlag);

		// simulate long duration update
		int loops = 1000/LONG_RUNNING_ACTION_SLEEP_TIME * LONG_RUNNING_ACTION_TIME;
		while (loops > 0) {
			LOG.info("{}", loops);
			sleep(LONG_RUNNING_ACTION_SLEEP_TIME);
			loops--;
		}
		
		// unlock children so they can try to update entity ( the should wait - due to pessimistic lock on DB)
		update(persisted, Thread.currentThread().getName());

		// wait for child threads
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

	private void await(final CountDownLatch readyFlag) {
		try {
			// wait for all children signal 'Ready to go' - entity to update has been selected
			readyFlag.await();
		} catch (InterruptedException e) {
			LOG.error("Error", e);
			return;
		}
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
		this.controller.create(car);
		return car;
	}

	private CarModel read(long carId) {
		LOG.info("Reading...");
		CarModel car = this.controller.read(carId);

		return car;
	}

	private void update(final CarModel car, String newName) {
		LOG.info("Updating... :: {}", car);
		try {
			car.setName(newName);
			this.controller.update(car);
			LOG.info("Updating... COMPLETE :: {}", car);
		} catch (HibernateOptimisticLockingFailureException e) {				// <---- Lock Handling
			LOG.error("Lock Exception :: lock found");
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
	
	private void showCar(CarModel car, WorkerService.Mode mode) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(car))) {
			LOG.info("{} :: NOT INITIALIZED", mode);
		} else if (Objects.isNull(car)) {
			LOG.info("{} :: NOT FOUND", mode);
		} else {
			LOG.info("{} :: {}", mode, car);
		}
	}
	
	class JPAThread extends Thread {

		private long carId;
		private CountDownLatch readyFlag;
		private CountDownLatch startFlag;
		private CountDownLatch completeFlag;

		public JPAThread(String name, long carId, CountDownLatch readyFlag, CountDownLatch startFlag,
				CountDownLatch completeFlag) {
			super(name);
			this.carId = carId;
			this.readyFlag = readyFlag;
			this.startFlag = startFlag;
			this.completeFlag = completeFlag;
		}

		@Override
		public void run() {
			
			CarModel persisted = read(carId);
			readyFlag.countDown();
			
			try {
				LOG.info("Waiting for main thread to let me start");
				startFlag.await();
			} catch (Exception e) {
				LOG.warn("Unable to wait for main thread to let me start", e);
				return;
			}
			
			update(persisted, getName());
			
			// allow main thread to release lock
			LOG.info("Complete");
			completeFlag.countDown();
		}
	}
}


