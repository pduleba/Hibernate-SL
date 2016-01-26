package com.pduleba.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.OwnerModel;

@Repository
@Transactional
public class OwnerDaoImpl extends AbstractDaoSupport<OwnerModel> implements OwnerDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OwnerDaoImpl.class);
	
	@Override
	public int getNumberOfOwners() {
		return DataAccessUtils.intResult(getSession().createQuery("select count(*) from OwnerModel").list());
	}

	// ------------------------------------------------
	//					Crete DB methods
	// ------------------------------------------------
	
	@Autowired
	public OwnerDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, OwnerModel.class);
	}

	@Override
	public List<?> queryForList() {
		return getSession().createSQLQuery("SELECT o.* FROM t_owner o").list();
	}
}