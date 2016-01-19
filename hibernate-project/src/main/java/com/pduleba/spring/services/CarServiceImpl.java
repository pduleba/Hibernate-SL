package com.pduleba.spring.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.dao.CarDao;

@Service
public class CarServiceImpl implements CarService {

	static final String REF_DAO_NAME = SpringConfiguration.DAO_JPA;
	
	@Resource(name = REF_DAO_NAME)
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
