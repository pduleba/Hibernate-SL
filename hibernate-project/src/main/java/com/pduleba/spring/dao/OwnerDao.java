package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.OwnerModel;

public interface OwnerDao {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<?> queryForList();

	List<?> queryForList(String carName);

	List<?> queryForList(int index, String carName);
}
