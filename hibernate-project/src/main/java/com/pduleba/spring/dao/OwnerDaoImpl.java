package com.pduleba.spring.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.OwnerModel;

@Repository
@Transactional
public class OwnerDaoImpl extends HibernateDaoSupport implements OwnerDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(OwnerDaoImpl.class);
	
	@Autowired
	public OwnerDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void create(OwnerModel owner) {
		getHibernateTemplate().save(owner);
	}

	@Override
	public OwnerModel read(long ownerId) {
		return getHibernateTemplate().get(OwnerModel.class, ownerId);
	}

	@Override
	public void update(OwnerModel owner) {
		getHibernateTemplate().update(owner);
	}

	@Override
	public void delete(OwnerModel owner) {
		getHibernateTemplate().delete(getHibernateTemplate().merge(owner));
	}
}
