package com.pduleba.spring.controller;

import static com.pduleba.spring.services.UtilityService.Mode.CREATE;
import static com.pduleba.spring.services.UtilityService.Mode.DELETE;
import static com.pduleba.spring.services.UtilityService.Mode.READ;
import static com.pduleba.spring.services.UtilityService.Mode.UPDATE;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.services.CarService;
import com.pduleba.spring.services.UtilityService;

@Component
public class MainControllerImpl implements MainController, PersistanceDaoBeans {

	public static final Logger LOG = LoggerFactory.getLogger(MainControllerImpl.class);

	// CONFIGURATION : Here you can switch Jpa (EntityManager) and Hibernate (SessionFactory) DB access API
	public static final String REF_DAO_NAME = BEAN_NAME_JPA;
	
	@Autowired
	private CarService carService;
	@Autowired
	private UtilityService utils;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;

	@Override
	public void execute() {
		showActivePersistanceModeDetails();
		
		LOG.info("------------");
		Long carId = create();
		LOG.info("------------");
		CarModel persisted = read(carId);
		LOG.info("------------");
		update(persisted, Thread.currentThread().getName());
		LOG.info("------------");
		delete(persisted);
		LOG.info("------------");
	}
	
	private void showActivePersistanceModeDetails() {
		switch (REF_DAO_NAME) {
			case BEAN_NAME_JPA:
				LOG.info("EntityManager is active");
				break;
			case BEAN_NAME_HIBERNATE:
				LOG.info("HibernateTemplate is active");
				break;
			default:
				throw new IllegalArgumentException(MessageFormat.format("Unknown ref dao bean name {0}", REF_DAO_NAME));
		}
	}

	private Long create() {
		CarModel car = utils.getCar();
		utils.showCar(car, CREATE);
		carService.create(car);
		
		return car.getId();
	}

	private CarModel read(long carId) {
		CarModel car = carService.read(carId);
		utils.showCar(car, READ);
		
		return car;
	}

	private void update(CarModel car, String newName) {
		car.setName(newName);
		utils.showCar(car, UPDATE);
		carService.update(car);
	}

	private void delete(CarModel car) {
		if (deleteEnabled) {
			carService.delete(car);
			CarModel deleted = carService.read(car.getId());
			utils.showCar(deleted, DELETE);
		} else {
			LOG.warn("Delete feature disabled!");
		}
	}
}
