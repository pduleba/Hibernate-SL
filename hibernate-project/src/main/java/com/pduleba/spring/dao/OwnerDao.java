package com.pduleba.spring.dao;

import com.pduleba.hibernate.model.OwnerModel;

public interface OwnerDao {

	void create(OwnerModel owner);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);
	
}
