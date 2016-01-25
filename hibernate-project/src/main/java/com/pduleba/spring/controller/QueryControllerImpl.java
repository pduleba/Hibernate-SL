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
		LOG.info("########### Execute JPQL SELECT ###########");
		utils.show(ownerSerivce.executeSelect());
		LOG.info("########### Execute JPQL SELECT WITH JOIN ###########");
		utils.show(ownerSerivce.executeSelectWithJoin());
		LOG.info("########### Execute JPQL SELECT BY ENUM PAST ###########");
		utils.show(ownerSerivce.executeSelectByEnum(OwnerType.PAST));
		LOG.info("########### Execute JPQL SELECT WITH FUNCTION max() ###########");
		utils.show(ownerSerivce.executeSelectWithFunctionMax());
		
		LOG.info("########### Execute JPQL SELECT WITH GROUP BY ###########");
		utils.show(ownerSerivce.executeSelectWithGroupBy());
		LOG.info("########### Execute JPQL SELECT WITH ORDER BY ###########");
		utils.show(ownerSerivce.executeSelectWithOrderBy());
		LOG.info("########### Execute JPQL SELECT WITH HAVING ###########");
		utils.show(ownerSerivce.executeSelectWithHaving());
		
//		LOG.info("Execute JPQL dynamic query WITH POSITIONAL BINDING()");
//		ownerSerivce.executeDynamicQueryWithPositionalBinding();
//		LOG.info("Execute JPQL dynamic query WITH NAME BINDING()");
//		ownerSerivce.executeDynamicQueryWithNameBinding();
	}

}
