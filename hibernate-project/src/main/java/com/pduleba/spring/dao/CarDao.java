package com.pduleba.spring.dao;

import org.hibernate.LockMode;

import com.pduleba.hibernate.model.CarModel;

public interface CarDao {

	void create(CarModel car);

	CarModel read(long carId);

	void update(CarModel car);

	void delete(CarModel car);

	void lock(CarModel car, LockMode lockMode);
	
}
