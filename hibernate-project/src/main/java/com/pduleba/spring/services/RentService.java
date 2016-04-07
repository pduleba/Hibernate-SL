package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.Rent;
import com.pduleba.hibernate.model.Terminal;
import com.pduleba.hibernate.model.User;

public interface RentService {

	void addRent(Rent rent);

	void addRent(Rent r, Terminal t, User c, User e);

	void deleteRent(Rent rent);
	
	List<Rent> getAllRents();
	
	void updateRent(Rent rent);
	
	Rent getRentById(long id);
	
	List<Rent> getRentByIdTerminal(long id, String date);
}
