package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.AbstractCarModel;

@Repository
@Transactional
public class CarDaoImpl extends HibernateDaoSupport implements CarDao {
	
	@Autowired
	public CarDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public void saveAll(Collection<AbstractCarModel> cars) {
		for (AbstractCarModel car : cars) {
			getHibernateTemplate().saveOrUpdate(car);
		}
	}

	@Override
	public List<AbstractCarModel> getAllCars() {
		return getHibernateTemplate().loadAll(AbstractCarModel.class);
	}

	@Override
	public void removeAll(List<AbstractCarModel> cars) {
		getHibernateTemplate().deleteAll(cars);
	}

}
