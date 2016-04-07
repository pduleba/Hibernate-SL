package com.pduleba.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.Rent;

import lombok.Data;

@Data
@Repository
public class RentDaoImpl implements RentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addRent(Rent rent) {
		sessionFactory.getCurrentSession().save(rent);
	}

	@Override
	public void deleteRent(Rent rent) {
		rent = (Rent) sessionFactory.getCurrentSession().get(Rent.class, rent.getIdRent());
		sessionFactory.getCurrentSession().delete(rent);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getAllRents() {
		return sessionFactory.getCurrentSession().getNamedQuery("get.All.Rents").list();
	}

	@Override
	public void updateRent(Rent rent) {
		sessionFactory.getCurrentSession().update(rent);
	}

	@Override
	public Rent getRentById(long id) {
		return (Rent) sessionFactory.getCurrentSession().get(Rent.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getRentByIdTerminal(long id, String date) {
		try {
			Query query = sessionFactory.getCurrentSession().getNamedQuery("get.Rent.By.Terminal.And.Date");
			query.setLong(0, id);
			query.setString(1, date);
			List<Rent> results = new ArrayList<Rent>();
			results = query.list();
			return results;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteAllRents() {
		for (Rent rent : getAllRents()) {
			deleteRent(rent);
		}

	}

}