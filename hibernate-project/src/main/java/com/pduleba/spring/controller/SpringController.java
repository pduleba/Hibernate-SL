package com.pduleba.spring.controller;

import org.hibernate.LockMode;

import com.pduleba.hibernate.model.CarModel;

public interface SpringController {

	void create(CarModel car);
	CarModel read(long carId);
	void update(CarModel car);
	void delete(CarModel car);
	
	void lock(CarModel car, LockMode lockMode);

}
