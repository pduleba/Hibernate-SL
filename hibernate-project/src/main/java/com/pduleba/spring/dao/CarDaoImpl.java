package com.pduleba.spring.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.CarModel;

@Repository
@Transactional
public class CarDaoImpl extends HibernateDaoSupport implements CarDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);
	
	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void create(CarModel car) {
		getHibernateTemplate().save(car);
	}

	@Override
	public CarModel read(long carId) {
		return getHibernateTemplate().get(CarModel.class, carId);
	}

	@Override
	public void update(CarModel car) {
		getHibernateTemplate().update(car);
	}

	@Override
	public void delete(CarModel car) {
		getHibernateTemplate().delete(getHibernateTemplate().merge(car));
	}
}
