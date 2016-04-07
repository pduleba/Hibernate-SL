package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainControllerImpl implements MainController {

	public static final Logger LOG = LoggerFactory.getLogger(MainControllerImpl.class);
	
	@Autowired
	private RentController rentController;

	@Override
	public void execute() {
//		rentController.execute();
	}
}
