package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.AbstractCarModel;
import com.pduleba.spring.dao.CarDao;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDao carDao;

	@Override
	public void saveCars(Collection<AbstractCarModel> cars) {
		carDao.saveAll(cars);
	}

	@Override
	public List<AbstractCarModel> getAllCars() {
		return carDao.getAllCars();
	}

	@Override
	public void removeAll(List<AbstractCarModel> cars) {
		carDao.removeAll(cars);
	}
	
	
}
