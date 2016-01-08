package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.CarModel;

public interface CarDao {

	List<CarModel> getAllCars();

	void saveAll(Collection<CarModel> cars);

	void removeAll(List<CarModel> cars);
}
