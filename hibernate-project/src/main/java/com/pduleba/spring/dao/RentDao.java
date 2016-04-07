package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.Rent;

public interface RentDao {

	void addRent(Rent rent);
	
	void deleteRent(Rent rent);
	
	List<Rent> getAllRents();
	
	void updateRent(Rent rent);
	
	Rent getRentById(long id);
	
	List<Rent> getRentByIdTerminal(long id, String date);
	
	void deleteAllRents();
	
}
