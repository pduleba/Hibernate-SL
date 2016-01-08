package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.AbstractCarModel;

public interface SpringController {

	void saveCars(Collection<AbstractCarModel> cars);
	List<AbstractCarModel> getAllCars();
	void removeCars(List<AbstractCarModel> cars);

}
