package com.pduleba.hibernate;

import java.util.Objects;

import javax.persistence.Persistence;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
	
	private void run() {
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
		
		LOG.info("Complete");
	}
	
	private CarModel create() {
		CarModel car = worker.getCar();
		this.controller.create(car);
		
		return car;
	}

	private CarModel read(long carId) {
		CarModel car = this.controller.read(carId);
		hasLobsInitialized(car, WorkerService.Mode.READ);
	
		return car;
	}
	
	private void hasLobsInitialized(CarModel car, WorkerService.Mode mode) {
		if (BooleanUtils.isFalse(Persistence.getPersistenceUtil().isLoaded(car))) {
			LOG.info("{} :: NOT INITIALIZED", mode);
		} else if (Objects.isNull(car)) {
			LOG.info("{} :: NOT FOUND", mode);
		} else {
			if (Hibernate.isPropertyInitialized(car, "image")) {
				LOG.info("{} :: Blob image INITIALIZED", mode);
			} else {
				LOG.info("{} :: Blob image NOT INITIALIZED ", mode);
			}
		}
	}
}


