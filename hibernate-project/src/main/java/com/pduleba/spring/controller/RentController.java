package com.pduleba.spring.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.Rent;
import com.pduleba.hibernate.model.Terminal;
import com.pduleba.hibernate.model.User;
import com.pduleba.spring.services.RentService;
import com.pduleba.spring.services.TerminalService;
import com.pduleba.spring.services.UserService;

@Component
public class RentController {
	
	final static Logger LOGGER = Logger.getLogger(RentController.class);
	
	@Autowired
	private RentService rentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TerminalService terminalService;

	private Terminal terminal;

	private User customer;

	private User employee;
	
	public void initialize(){
		long terminalId = 1;
		long customerId = 1;
		long employeeId = 1;
		
		terminal = terminalService.getTerminalById(terminalId);
		customer = userService.getUserById(customerId);
		employee = userService.getUserById(employeeId);
	}
	
	public void execute(){
		Rent rent = Rent.builder().price(2.0D).date("2000-01-01 12:00:00").hours(1).build();
		
		rent.setTerminal(terminal);
		rent.setCustomer(customer);
		rent.setEmployee(employee);
		
		rentService.addRent(rent);
	}

}
