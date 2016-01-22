package com.pduleba.spring.services;

import com.pduleba.hibernate.model.CarModel;

public interface UtilityService {
	
	public enum Mode {
		CREATE,
		READ,
		UPDATE,
		DELETE
	}
	
	CarModel getCar();
	
	void showCar(Object entity, Mode mode);
}
