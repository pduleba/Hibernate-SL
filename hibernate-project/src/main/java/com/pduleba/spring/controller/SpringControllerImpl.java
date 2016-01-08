package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.AbstractCarModel;
import com.pduleba.spring.services.CarService;

@Component
public class SpringControllerImpl implements SpringController {

	public static final Logger LOG = LoggerFactory.getLogger(SpringControllerImpl.class);
	
	@Autowired
	private CarService carService;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;

	@Override
	public void saveCars(Collection<AbstractCarModel> users) {
		carService.saveCars(users);
	}

	@Override
	public List<AbstractCarModel> getAllCars() {
		return carService.getAllCars();
	}

	@Override
	public void removeCars(List<AbstractCarModel> users) {
		if (deleteEnabled) {
			this.carService.removeAll(users);
		}
	}
	
	
}
