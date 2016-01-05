package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.CarModel;

@Repository
@Transactional
public class CarDaoImpl extends HibernateDaoSupport implements CarDao {
	
	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveAll(Collection<CarModel> cars) {
		for (CarModel car : cars) {
			getHibernateTemplate().saveOrUpdate(car);
		}
	}

	@Override
	public List<CarModel> getAllCars() {
		return getHibernateTemplate().loadAll(CarModel.class);
	}

	@Override
	public void removeAll(List<CarModel> cars) {
		getHibernateTemplate().deleteAll(cars);
	}

}
