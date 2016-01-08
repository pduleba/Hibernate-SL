package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.AbstractCarModel;

public interface CarDao {

	List<AbstractCarModel> getAllCars();

	void saveAll(Collection<AbstractCarModel> cars);

	void removeAll(List<AbstractCarModel> cars);
}
