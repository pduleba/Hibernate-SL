package com.pduleba.spring.services;

import com.pduleba.hibernate.model.OwnerModel;

public interface OwnerService {

	void create(OwnerModel owner);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

}
