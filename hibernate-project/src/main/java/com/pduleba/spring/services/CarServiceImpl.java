package com.pduleba.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.dao.CarDao;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	public void create(CarModel car) {
		carDao.create(car);
	}

	@Override
	public CarModel read(long carId) {
		return carDao.read(carId);
	}

	@Override
	public void update(CarModel car) {
		carDao.update(car);
	}

	@Override
	public void delete(CarModel car) {
		carDao.delete(car);
	}
	
}
