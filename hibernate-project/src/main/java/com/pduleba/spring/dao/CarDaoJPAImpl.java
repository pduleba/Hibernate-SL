package com.pduleba.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.CarModel;

@Repository(value = SpringConfiguration.DAO_JPA)
@Transactional(value = SpringConfiguration.TRANSACTION_MANAGER_JPA)
public class CarDaoJPAImpl implements CarDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public CarDaoJPAImpl() {
	}

	@Override
	public void create(CarModel car) {
		em.persist(car);
	}

	@Override
	public CarModel read(long carId) {
		return em.find(CarModel.class, carId);
	}

	@Override
	public void update(CarModel car) {
		em.merge(car);
	}

	@Override
	public void delete(CarModel car) {
		em.remove(em.contains(car) ? car : em.merge(car));	}
}
