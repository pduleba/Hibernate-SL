package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<OwnerModel> executeSelect();

	List<OwnerModel> executeSelectWithJoin();

	List<?> executeSelectWithGroupBy();

	List<OwnerModel> executeSelectWithOrderBy();

	List<OwnerModel> executeSelectWithHaving();

	Long executeSelectWithFunctionMax();

	List<OwnerModel> executeSelectByEnum(OwnerType type);

}
