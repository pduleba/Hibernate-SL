package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.LockMode;
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
import com.pduleba.utils.LockDetails;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	// seconds = 1000/INTERVAL_DURATION*SLEEP_TIME
	private final int SLEEP_TIME = 25;
	private final int INTERVAL_DURATION = 500;
	private final int THREADS_NUMBER = 5;
	
	// synchronizes all threads to be ready to start
	final CountDownLatch READY_FLAG = new CountDownLatch(THREADS_NUMBER);
	// synchronizes all threads to start at same time
	final CountDownLatch START_FLAG = new CountDownLatch(1);
	
	private SpringController controller;
	private WorkerService worker;

	public static void main(String[] args) {
		new Main().run();
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
		JPAThread thread = null;
		String threadName;
		List<JPAThread> threads = new LinkedList<>();
		CarModel persisted = create();
		long carId = persisted.getId();
		LOG.info("Car id = {}", carId);
		
		for (int i = 0; i < THREADS_NUMBER; i++) {
			threadName = MessageFormat.format("thread-{0}", i);
			thread = new JPAThread(threadName, carId, READY_FLAG, START_FLAG);
			thread.start();
			threads.add(thread);
		}

		// Await for children to signal they are ready to go
		await(READY_FLAG);

		// lock entity !!!INSIDE OF TRANSACTION!!! so children 
		// threads will will not be able COMPLETE to update it 
		lock(persisted, getLockDetails());

		// wait for child threads
		join(threads);
		
		LOG.info("Complete");
	}

	private void update(final CarModel car, String newName) {
		LOG.info("Updating... :: {}", car);
		try {
			car.setName(newName);
			this.controller.update(car);
			LOG.info("Updating... COMPLETE :: {}", car);
		} catch (HibernateOptimisticLockingFailureException e) {				// <---- Lock Handling
			LOG.error("Updating... ERROR :: lock found");
		}
	}


	private LockDetails getLockDetails() {
		return new LockDetails(LockMode.UPGRADE_NOWAIT, SLEEP_TIME, INTERVAL_DURATION, START_FLAG,
				LOG);
	}

	private void join(List<JPAThread> threads) {
		try {
			for (JPAThread t : threads) {
				t.join();
			}
			threads.clear();
		} catch (InterruptedException e) {
			LOG.error("Unable to join", e);
			return;
		}
	}

	private void await(final CountDownLatch readyFlag) {
		try {
			// wait for all children signal 'Ready to go' - entity to update has been selected
			readyFlag.await();
		} catch (InterruptedException e) {
			LOG.error("Await error", e);
			return;
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
	
//	private void delete(CarModel car) {
//		LOG.info(" ----- DELETE ----- ");
//		this.controller.delete(car);
//		this.worker.showCar(car, Mode.DELETE);
//	}

	private void lock(final CarModel car, LockDetails lockDetails) {
		this.controller.lock(car, lockDetails);
	}
	
//	private void lock(final CarModel car, LockModeType lockMode, int timeout) {
//		lock(car, LockModeConverter.convertToLockMode(lockMode), timeout);
//	}
//	
//	private void showCar(CarModel car, WorkerService.Mode mode) {
//		if (BooleanUtils.isFalse(Hibernate.isInitialized(car))) {
//			LOG.info("{} :: NOT INITIALIZED", mode);
//		} else if (Objects.isNull(car)) {
//			LOG.info("{} :: NOT FOUND", mode);
//		} else {
//			LOG.info("{} :: {}", mode, car);
//		}
//	}
	
	class JPAThread extends Thread {

		private long carId;
		private CountDownLatch readyFlag;
		private CountDownLatch startFlag;

		public JPAThread(String name, long carId, CountDownLatch readyFlag, CountDownLatch startFlag) {
			super(name);
			this.carId = carId;
			this.readyFlag = readyFlag;
			this.startFlag = startFlag;
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
		}
	}
}


