package com.pduleba.hibernate;

import java.util.Collection;
import java.util.List;

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

	private void run() {
		ClassPathResource log4jResource = new ClassPathResource("classpath:/config/log4j.properties");
		System.setProperty("log4j.configurationFile", log4jResource.getPath());
		
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			this.worker = ctx.getBean(WorkerService.class);
			
			LOG.info("Starting...");
	
			LOG.info("######## CRUDS ######## ");
			executeCarsCRUD();
		}
	}

	private void executeCarsCRUD() {
		saveCars();
		LOG.info(" ----- CREATE complete ----- ");
		List<CarModel> allCars = getAllCars();
		LOG.info(" ----- READ complete ----- ");
		removeAllCars(allCars);
		LOG.info(" ----- DELETE complete ----- ");
		getAllCars();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void removeAllCars(List<CarModel> cars) {
		this.controller.removeCars(cars);
	}

	private List<CarModel> getAllCars() {
		List<CarModel> cars = this.controller.getAllCars();
		worker.showCars(cars);

		return cars;
	}

	private void saveCars() {
		Collection<CarModel> cars = worker.getCars();
		this.controller.saveCars(cars);
	}

}
