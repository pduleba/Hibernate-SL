package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;

public interface WorkerService {

	enum Mode {
		BEFORE_READ,
		AFTER__READ,
		BEFORE_UPDATE,
		AFTER__UPDATE
	}
	
	CarModel getCar();
}
