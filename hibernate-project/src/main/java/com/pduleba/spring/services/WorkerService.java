package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;

public interface WorkerService {

	enum Mode {
		CREATE,
		READ,
		UPDATE,
		DELETE
	}
	
	CarModel getCar();

	void showCar(CarModel car, Mode mode);
}
