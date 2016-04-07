package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.Rent;

public interface RentService {

	void addRent(Rent rent);
	
	void deleteRent(Rent rent);
	
	List<Rent> getAllRents();
	
	void updateRent(Rent rent);
	
	Rent getRentById(long id);
	
	List<Rent> getRentByIdTerminal(long id, String date);
}
