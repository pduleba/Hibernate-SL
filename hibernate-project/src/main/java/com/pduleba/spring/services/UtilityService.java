package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;

public interface UtilityService {
	
	CarModel getCar();
	
	void showCar(Object entity);
}
