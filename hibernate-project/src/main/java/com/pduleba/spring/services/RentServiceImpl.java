package com.pduleba.spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.Rent;
import com.pduleba.hibernate.model.Terminal;
import com.pduleba.hibernate.model.User;
import com.pduleba.spring.dao.RentDaoImpl;

@Service("rentService")
@Transactional
public class RentServiceImpl implements RentService {

	final static Logger LOG = Logger.getLogger(RentServiceImpl.class);
	
	@Autowired
	private RentDaoImpl rentDao;

	@Override
	public void addRent(Rent rent) {
		rentDao.addRent(rent);
	}

	@Override
	public void addRent(Rent r, Terminal t, User c, User e) {
		try {
			t = (Terminal) rentDao.getSessionFactory().getCurrentSession().merge(t);
			c = (User) rentDao.getSessionFactory().getCurrentSession().merge(c);
			e = (User) rentDao.getSessionFactory().getCurrentSession().merge(e);
			
			r.setTerminal(t);
			r.setCustomer(c);
			r.setEmployee(e);
			
			rentDao.addRent(r);
			LOG.info("Success :: ");
		} catch (Exception ex) {
			LOG.error("Error :: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRent(Rent rent) {
		rentDao.deleteRent(rent);
	}

	@Override
	public List<Rent> getAllRents() {
		return rentDao.getAllRents();
	}

	@Override
	public void updateRent(Rent rent) {
		Rent rentToUpdate = getRentById(rent.getIdRent());
		rentToUpdate.setTerminal(rent.getTerminal());
		rentToUpdate.setCustomer(rent.getCustomer());
		rentToUpdate.setEmployee(rent.getEmployee());
		rentToUpdate.setDate(rent.getDate());
		rentToUpdate.setTimeStart(rent.getTimeStart());
		rentToUpdate.setHours(rent.getHours());
		rentDao.updateRent(rentToUpdate);
	}

	@Override
	public Rent getRentById(long id) {
		return rentDao.getRentById(id);
	}

	@Override
	public List<Rent> getRentByIdTerminal(long id, String date) {
		return rentDao.getRentByIdTerminal(id, date);
	}

}
