package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.CarModel;

public interface SpringController {

	void saveCars(Collection<CarModel> cars);
	List<CarModel> getAllCars();
	void removeCars(List<CarModel> cars);

}
