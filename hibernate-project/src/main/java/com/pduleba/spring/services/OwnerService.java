package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;
import com.pduleba.hibernate.model.constructor.FirstAndLastName;
import com.pduleba.spring.dao.parameters.OwnerParamterValueBean;

public interface OwnerService {

	void create(OwnerModel owner);

	void createAll(List<OwnerModel> owners);

	OwnerModel read(long ownerId);

	void update(OwnerModel owner);

	void delete(OwnerModel owner);

	int getNumberOfOwners();

	List<OwnerModel> queryForList();

	List<OwnerModel> queryWithJoin();

	List<?> queryWithGroupBy();

	List<OwnerModel> queryWithOrderBy();

	List<?> queryWithHaving();

	Long queryForAggregateFunction();

	List<OwnerModel> queryByEnum(OwnerType type);

	List<OwnerModel> queryWithNumericBinding();

	List<OwnerModel> queryWithNameBinding();

	OwnerModel queryForSingleObject();

	Object queryForSingleField();

	List<OwnerModel> queryForArrayOfFields();

	List<FirstAndLastName> queryWithProjectionAndConstructor();

	List<?> executeSelectWithFetchSize(int fetchSize);

	List<?> executeSelectWithJoin(OwnerParamterValueBean param);

}
