package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.services.CarService;

@Component
public class SpringControllerImpl implements SpringController {

	public static final Logger LOG = LoggerFactory.getLogger(SpringControllerImpl.class);
	
	@Autowired
	private CarService carService;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;

	@Override
	public void create(CarModel car) {
		carService.create(car);
	}

	@Override
	public CarModel read(long carId) {
		return carService.read(carId);
	}

	@Override
	public void update(CarModel car) {
		this.carService.update(car);
	}

	@Override
	public void delete(CarModel car) {
		if (deleteEnabled) {
			this.carService.delete(car);
		}
	}
	
	
}
