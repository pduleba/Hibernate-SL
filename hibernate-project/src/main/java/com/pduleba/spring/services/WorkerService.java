package com.pduleba.spring.services;

import java.util.Collection;

import com.pduleba.hibernate.model.AbstractCarModel;

public interface WorkerService {

	void showCars(Collection<AbstractCarModel> Cars);

	Collection<AbstractCarModel> getCars();

}
