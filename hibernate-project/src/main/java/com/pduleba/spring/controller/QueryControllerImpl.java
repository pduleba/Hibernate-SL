package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.spring.services.CarService;
import com.pduleba.spring.services.OwnerService;
import com.pduleba.spring.services.UtilityService;

import lombok.Data;

@Component
public @Data class QueryControllerImpl implements QueryController {

	public static final Logger LOG = LoggerFactory.getLogger(QueryControllerImpl.class);

	@Autowired
	private OwnerService ownerSerivce;
	@Autowired
	private CarService carSerivce;
	@Autowired
	private UtilityService utils;
	
	@Override
	public void executeQueries() {
		LOG.info("########### Execute Query for List ###########");
		utils.show(ownerSerivce.queryForList());
		LOG.info("########### Execute Query for List by named parameter ###########");
		utils.show(ownerSerivce.queryForList("Audi-2-2"));
		LOG.info("########### Execute Query for List by index parameter ###########");
		utils.show(ownerSerivce.queryForList(0, "Audi-2-2"));
	}

}
