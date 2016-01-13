package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;

public interface CarService {

	void create(CarModel car);

	CarModel read(long carId);

	void update(CarModel car);

	void delete(CarModel car);

}
