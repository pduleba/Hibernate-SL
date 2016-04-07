package com.pduleba.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.Address;
import com.pduleba.spring.services.AddressService;

@Component
public class MainControllerImpl implements MainController {

	public static final Logger LOG = LoggerFactory.getLogger(MainControllerImpl.class);
	
	@Autowired
	private RentController rentController;
	@Autowired
	private AddressService addressService;

	@Override
	public void execute() {
		List<Address> allAddress = addressService.getAllAddress();
		
		LOG.debug("Address count :: " + allAddress.size());
		rentController.execute();
	}
}
