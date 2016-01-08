package com.pduleba.spring.services;

import java.util.Collection;

import com.pduleba.hibernate.model.CarModel;

public interface WorkerService {

	void showCars(Collection<CarModel> Cars);

	Collection<CarModel> getCars();

}
