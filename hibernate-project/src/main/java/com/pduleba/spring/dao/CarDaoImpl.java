package com.pduleba.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.CarModel;

@Repository
@Transactional
public class CarDaoImpl extends AbstractDaoSupport<CarModel> implements CarDao {
	
	public static final Logger LOG = LoggerFactory.getLogger(CarDaoImpl.class);
	
	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory, CarModel.class);
	}

	@Override
	public void create(CarModel car) {
		getSession().save(car);
	}

	@Override
	public CarModel read(long carId) {
		return getSession().get(CarModel.class, carId);
	}

	@Override
	public void update(CarModel car) {
		Session session = getSession();
		session.update(session.contains(car) ? car : session.merge(car));
	}

	@Override
	public void delete(CarModel car) {
		Session session = getSession();
		session.delete(session.contains(car) ? car : session.merge(car));
	}
}
