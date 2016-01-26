package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.OwnerType;
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
		LOG.info("########### Execute Query for single object ###########");
		utils.show(ownerSerivce.queryForSingleObject());
		LOG.info("########### Execute Query for single field ###########");
		utils.show(ownerSerivce.queryForSingleField());
		LOG.info("########### Execute Query for array of fields ###########");
		utils.show(ownerSerivce.queryForArrayOfFields());
		LOG.info("########### Execute Query for aggregate function ###########");
		utils.show(ownerSerivce.queryForAggregateFunction());
		LOG.info("########### Execute Query by Enum ###########");
		utils.show(ownerSerivce.queryByEnum(OwnerType.PAST));
		LOG.info("########### Execute Query with Projection and Constructor ###########");
		utils.show(ownerSerivce.queryWithProjectionAndConstructor());

		LOG.info("########### Execute Query with positional binding ###########");
		utils.show(ownerSerivce.queryWithNumericBinding());
		LOG.info("########### Execute Query with name binding ###########");
		utils.show(ownerSerivce.queryWithNameBinding());
		
		LOG.info("########### Execute Query with JOIN ###########");
		utils.show(ownerSerivce.queryWithJoin());
		
		LOG.info("########### Execute Query with GROUP BY ###########");
		utils.show(ownerSerivce.queryWithGroupBy());
		LOG.info("########### Execute Query with ORDER BY ###########");
		utils.show(ownerSerivce.queryWithOrderBy());
		LOG.info("########### Execute Query with HAVING ###########");
		utils.show(ownerSerivce.queryWithHaving());
		
		LOG.info("########### Execute Query with LEFT JOIN ###########");
		utils.show(ownerSerivce.queryWithLeftJoin());
		
		LOG.info("########### Execute TODO ###########");
		utils.show(ownerSerivce.executeSelectWithFetchSize(20));
	}

}
