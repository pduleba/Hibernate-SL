package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;
import com.pduleba.hibernate.model.constructor.FirstAndLastName;
import com.pduleba.spring.dao.OwnerDao;
import com.pduleba.spring.dao.parameters.OwnerParamterValueBean;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Override
	public void create(OwnerModel owner) {
		ownerDao.create(owner);
	}
	
	@Override
	public void createAll(List<OwnerModel> owners) {
		ownerDao.createAll(owners);
	}

	@Override
	public OwnerModel read(long ownerId) {
		return ownerDao.read(ownerId);
	}

	@Override
	public void update(OwnerModel owner) {
		ownerDao.update(owner);
	}

	@Override
	public void delete(OwnerModel owner) {
		ownerDao.delete(owner);
	}

	@Override
	public int getNumberOfOwners() {
		return ownerDao.getNumberOfOwners();
	}

	// ------------------------------------------------
	//					Query methods
	// ------------------------------------------------

	@Override
	public List<OwnerModel> queryForList() {
		return ownerDao.queryForList();
	}
	
	@Override
	public List<OwnerModel> queryWithJoin() {
		return ownerDao.queryWithJoin();
	}
	
	@Override
	public List<OwnerModel> queryByEnum(OwnerType type) {
		 return ownerDao.queryByEnum(type);
	}
	
	@Override
	public Long queryForAggregateFunction() {
		 return ownerDao.queryForAggregateFunction();
	}
	
	@Override
	public List<?> queryWithGroupBy() {
		 return ownerDao.queryWithGroupBy();
	}
	
	@Override
	public List<?> queryWithHaving() {
		 return ownerDao.queryWithHaving();
	}
	
	@Override
	public List<OwnerModel> queryWithOrderBy() {
		 return ownerDao.queryWithOrderBy();
	}
	
	@Override
	public List<OwnerModel> queryWithNumericBinding() {
		return ownerDao.queryWithNumericBinding();
	}

	@Override
	public List<OwnerModel> queryWithNameBinding() {
		return ownerDao.queryWithNameBinding();
	}

	@Override
	public OwnerModel queryForSingleObject() {
		return ownerDao.queryForSingleObject();
	}

	@Override
	public Object queryForSingleField() {
		return ownerDao.queryForSingleField();
	}

	@Override
	public List<OwnerModel> queryForArrayOfFields() {
		return ownerDao.queryForArrayOfFields();
	}
	
	@Override
	public List<FirstAndLastName> queryWithProjectionAndConstructor() {
		return ownerDao.queryWithProjectionAndConstructor();
	}
	
	@Override
	public List<?> executeSelectWithFetchSize(int fetchSize) {
		return ownerDao.executeSelectWithFetchSize(fetchSize);
	}
	
	@Override
	public List<?> executeSelectWithJoin(OwnerParamterValueBean param) {
		return ownerDao.executeSelectWithJoin(param);
	}
}
