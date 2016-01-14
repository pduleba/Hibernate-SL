package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.controller.MainController;
import com.pduleba.spring.services.WorkerService;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	private MainController controller;
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
			this.controller = ctx.getBean(MainController.class);
			this.worker = ctx.getBean(WorkerService.class);
			
			LOG.info("##### Starting... #####");
			start();
		}
	}
	
	private void start() {
		CarModel persisted = create();
		read(persisted.getId());
		update(persisted, "updated");
		
		LOG.info("Complete");
	}

	private void update(final CarModel car, String newName) {
		try {
			car.setName(newName);
			this.showCar(car, WorkerService.Mode.UPDATE);
			
			this.controller.update(car);
		} catch (HibernateOptimisticLockingFailureException e) {				// <---- Lock Handling
			LOG.error("Updating... ERROR :: lock found");
		}
	}
	
	private CarModel create() {
		CarModel car = worker.getCar();
		this.showCar(car, WorkerService.Mode.CREATE);
		this.controller.create(car);
		
		return car;
	}

	private CarModel read(long carId) {
		CarModel car = this.controller.read(carId);
		this.showCar(car, WorkerService.Mode.READ);
		
		return car;
	}
	
	private void showCar(CarModel car, WorkerService.Mode mode) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(car))) {
			LOG.info("{} :: NOT INITIALIZED", mode, car);
		} else if (Objects.isNull(car)) {
			LOG.info("{} :: NOT FOUND", mode, car);
		} else {
			LOG.info("{} :: {}", mode, car);
		}
	}
}


