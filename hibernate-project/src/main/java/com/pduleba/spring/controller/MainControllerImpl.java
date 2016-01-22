package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.services.CarService;
import com.pduleba.spring.services.UtilityService;

@Component
public class MainControllerImpl implements MainController {

	public static final Logger LOG = LoggerFactory.getLogger(MainControllerImpl.class);
	
	@Autowired
	private CarService carService;
	@Autowired
	private UtilityService utils;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;

	@Override
	public void execute() {
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
	
	private Long create() {
		CarModel car = utils.getCar();
		utils.showCar(car);
		carService.create(car);
		
		return car.getId();
	}

	private CarModel read(long carId) {
		CarModel car = carService.read(carId);
		utils.showCar(car);
		
		return car;
	}

	private void update(CarModel car, String newName) {
		car.setName(newName);
		utils.showCar(car);
		carService.update(car);
	}

	private void delete(CarModel car) {
		if (deleteEnabled) {
			carService.delete(car);
			CarModel deleted = carService.read(car.getId());
			utils.showCar(deleted);
		} else {
			LOG.warn("Delete feature disabled!");
		}
	}
}
