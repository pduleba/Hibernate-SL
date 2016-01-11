package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;

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

	private void run() {
		configureLogger();
		
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			this.worker = ctx.getBean(WorkerService.class);
			
			LOG.info("Starting...");
	
			LOG.info("######## CRUDS ######## ");
			executeCarsCRUD();
		}
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

	private void executeCarsCRUD() {
		CarModel transientCar = worker.getCar();
		
		LOG.info(" ----- CREATE ----- ");
		create(transientCar);
		LOG.info(" ----- READ ----- ");
		CarModel persisted = read(transientCar.getId());
		LOG.info(" ----- UPDATE ----- ");
		update(persisted);
		LOG.info(" ----- DELETE ----- ");
		delete(persisted);

		LOG.info("Complete");
	}

	private void create(CarModel car) {
		this.worker.showCar(car, Mode.CREATE);
		this.controller.create(car);
	}

	private CarModel read(long carId) {
		CarModel car = this.controller.read(carId);
		this.worker.showCar(car, Mode.READ);

		return car;
	}

	private void update(final CarModel car) {
		car.setName(null);
		
		this.controller.update(car);
		this.worker.showCar(car, Mode.UPDATE);
	}

	private void delete(CarModel car) {
		this.controller.delete(car);
		this.worker.showCar(car, Mode.DELETE);
	}
}
