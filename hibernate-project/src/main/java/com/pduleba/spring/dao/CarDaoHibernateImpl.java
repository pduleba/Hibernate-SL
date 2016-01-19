package com.pduleba.spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;

@Repository(value = SpringConfiguration.DAO_HIBERNATE)
@Transactional(value = SpringConfiguration.TRANSACTION_MANAGER_HIBERNATE)
public class CarDaoHibernateImpl extends HibernateDaoSupport implements CarDao {
	
	@Autowired
	public CarDaoHibernateImpl(SessionFactory sessionFactory) {
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
		getHibernateTemplate().delete(car);
	}
}
