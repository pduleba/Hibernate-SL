package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.AbstractCarModel;
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
		saveCars();
		LOG.info(" ----- CREATE complete ----- ");
		List<AbstractCarModel> allCars = getAllCars();
		LOG.info(" ----- READ complete ----- ");
		removeAllCars(allCars);
		LOG.info(" ----- DELETE complete ----- ");
		getAllCars();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void removeAllCars(List<AbstractCarModel> cars) {
		this.controller.removeCars(cars);
	}

	private List<AbstractCarModel> getAllCars() {
		List<AbstractCarModel> cars = this.controller.getAllCars();
		worker.showCars(cars);

		return cars;
	}

	private void saveCars() {
		Collection<AbstractCarModel> cars = worker.getCars();
		this.controller.saveCars(cars);
	}

}
