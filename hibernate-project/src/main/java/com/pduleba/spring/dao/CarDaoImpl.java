package com.pduleba.spring.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.utils.LockDetails;

@Repository
@Transactional
public class CarDaoImpl extends HibernateDaoSupport implements CarDao {
	
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
		getHibernateTemplate().delete(car);
	}

	@Override
	public void lock(CarModel car, LockDetails lockDetails) {
		getHibernateTemplate().lock(car, lockDetails.getLockMode());
		
		// allow childs thread to execute update while lock in progress
		lockDetails.getStartFlag().countDown();
		
		// simulate long duration update
		int interval = lockDetails.getInterval();
		int loops = (int)((double)(1000/(double)interval) * lockDetails.getSleepTime());
		while (loops > 0) {
			lockDetails.getLog().info("{}", loops);
			sleep(interval);
			loops--;
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
