package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.controller.SpringController;
import com.pduleba.spring.services.WorkerService;
import com.pduleba.spring.services.WorkerService.Mode;

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
		final int[] TIMEOUTS = {2000, 0};
		// this will cause to all thread to wait on start point
		final CyclicBarrier startPoint = new CyclicBarrier(TIMEOUTS.length);
		// this will start all thread waiting on start point
		final CountDownLatch readySetGoFlag = new CountDownLatch(1);
		
		JPAThread thread = null;
		String threadName;
		List<JPAThread> threads = new LinkedList<>();
		CarModel persisted = create();
		long carId = persisted.getId();
		
		for (int i = 0; i < TIMEOUTS.length; i++) {
			threadName = MessageFormat.format("thread-{0}", i);
			thread = new JPAThread(threadName, TIMEOUTS[i], carId, readySetGoFlag, startPoint);
			thread.start();
			threads.add(thread);
		}

		readySetGoFlag.countDown();
		
		try {
			for (JPAThread t : threads) {
				t.join();
			}
			threads.clear();
		} catch (InterruptedException e) {
			LOG.error("Unable to join", e);
		}
		
		LOG.info("Complete");
	}
	
	private CarModel create() {
		CarModel car = worker.getCar();
		
		LOG.info(" ----- CREATE ----- ");
		this.worker.showCar(car, Mode.CREATE);
		this.controller.create(car);
		
		return car;
	}

	private CarModel read(long carId) {
		LOG.info(" ----- READ ----- ");
		CarModel car = this.controller.read(carId);
		this.worker.showCar(car, Mode.READ);

		return car;
	}

	private void update(final CarModel car) {
		LOG.info(" ----- UPDATE ----- ");
		car.setName(null);
		
		this.controller.update(car);
		this.worker.showCar(car, Mode.UPDATE);
	}

//	private void delete(CarModel car) {
//		LOG.info(" ----- DELETE ----- ");
//		this.controller.delete(car);
//		this.worker.showCar(car, Mode.DELETE);
//	}

	class JPAThread extends Thread {

		private int waitTime;
		private long carId;
		private CountDownLatch flag;
		private CyclicBarrier startPoint;

		public JPAThread(String name, int waitTime, long carId, CountDownLatch flag, CyclicBarrier startPoint) {
			super(name);
			this.waitTime = waitTime;
			this.carId = carId;
			this.flag = flag;
			this.startPoint = startPoint;
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
			
			try {
				LOG.info("Waiting for main thread to let me start");
				flag.await();
			} catch (Exception e) {
				LOG.warn("Unable to wait for main thread to let me start");
				return;
			}

			LOG.info("Reading persisted object by id {}", carId);
			
			CarModel persisted = read(carId);
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				LOG.warn("Unable to wait for a duration of {} ms", waitTime);
				return;
			}
			
			LOG.info("Updating persisted object");
			update(persisted);
		}
	}
}


