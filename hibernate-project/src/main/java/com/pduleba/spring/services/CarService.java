package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.utils.LockDetails;

public interface CarService {

	void create(CarModel car);

	CarModel read(long carId);

	void update(CarModel car);

	void delete(CarModel car);

	void lock(CarModel car, LockDetails lockDetails);

}
