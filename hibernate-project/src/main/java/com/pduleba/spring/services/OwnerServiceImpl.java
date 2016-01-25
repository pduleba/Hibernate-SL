package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.OwnerModel;
import com.pduleba.hibernate.model.OwnerType;
import com.pduleba.spring.dao.OwnerDao;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerDao ownerDao;

	@Override
	public List<OwnerModel> executeSelect() {
		return ownerDao.executeSelect();
	}
	
	@Override
	public List<OwnerModel> executeSelectWithJoin() {
		return ownerDao.executeSelectWithJoin();
	}
	
	@Override
	public List<OwnerModel> executeSelectByEnum(OwnerType type) {
		 return ownerDao.executeSelectByEnum(type);
	}
	
	@Override
	public Long executeSelectWithFunctionMax() {
		 return ownerDao.executeSelectWithFunctionMax();
	}
	
	@Override
	public List<?> executeSelectWithGroupBy() {
		 return ownerDao.executeSelectWithGroupBy();
	}
	
	@Override
	public List<OwnerModel> executeSelectWithHaving() {
		 return ownerDao.executeSelectWithHaving();
	}
	
	@Override
	public List<OwnerModel> executeSelectWithOrderBy() {
		 return ownerDao.executeSelectWithOrderBy();
	}
	
	// ------------------------------------------------
	//					Crete DB methods
	// ------------------------------------------------
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
	
}
