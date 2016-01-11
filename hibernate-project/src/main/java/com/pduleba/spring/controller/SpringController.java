package com.pduleba.spring.controller;

import com.pduleba.hibernate.model.CarModel;

public interface SpringController {

	void create(CarModel car);
	CarModel read(long carId);
	void update(CarModel car);
	void delete(CarModel car);

}
