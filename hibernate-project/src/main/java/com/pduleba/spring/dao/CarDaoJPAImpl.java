package com.pduleba.spring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.controller.PersistanceDaoBeans;

@Repository(value = PersistanceDaoBeans.BEAN_NAME_JPA)
@Transactional
public class CarDaoJPAImpl implements CarDao {

	public static final Logger LOG = LoggerFactory.getLogger(CarDaoJPAImpl.class);
	
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
