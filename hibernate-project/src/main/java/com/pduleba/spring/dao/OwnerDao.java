package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;
import com.pduleba.spring.dao.parameters.OwnerParamterValueBean;

public interface OwnerDao {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<OwnerModel> executeSelect();

	List<OwnerModel> executeSelectWithJoin();

	List<OwnerModel> executeSelectByEnum(OwnerType type);

	Long executeSelectWithFunctionMax();

	List<?> executeSelectWithGroupBy();

	List<OwnerModel> executeSelectWithHaving();

	List<OwnerModel> executeSelectWithOrderBy();

	List<OwnerModel> executeSelectWithJoin(OwnerParamterValueBean valueBean);

	List<OwnerModel> executeSelectWithFetchSize(int fetchSize);
	
}
