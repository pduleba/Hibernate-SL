package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.AbstractCarModel;

public interface CarService {

	void saveCars(Collection<AbstractCarModel> cars);

	List<AbstractCarModel> getAllCars();

	void removeAll(List<AbstractCarModel> cars);

}
