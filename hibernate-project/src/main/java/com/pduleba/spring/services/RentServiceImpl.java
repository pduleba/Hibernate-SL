package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pduleba.hibernate.model.Rent;
import com.pduleba.spring.dao.RentDao;

@Service("rentService")
@Transactional
public class RentServiceImpl implements RentService {

	@Autowired
	RentDao rentDao;

	@Override
	public void addRent(Rent rent) {
		rentDao.addRent(rent);
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
