package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.CarModel;

public interface CarService {

	void saveCars(Collection<CarModel> cars);

	List<CarModel> getAllCars();

	void removeAll(List<CarModel> cars);

}
