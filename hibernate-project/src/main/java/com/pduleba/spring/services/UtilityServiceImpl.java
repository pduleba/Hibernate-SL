package com.pduleba.spring.services;

import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pduleba.configuration.ApplicationPropertiesConfiguration;
import com.pduleba.hibernate.model.CarModel;

@Component
class UtilityServiceImpl implements UtilityService, ApplicationPropertiesConfiguration {

	public static final Logger LOG = LoggerFactory.getLogger(UtilityServiceImpl.class);

	@Override
	public CarModel getCar() {
		return new CarModel();
	}

	@Override
	public void showCar(Object entity) {
		if (Objects.isNull(entity)) {
			LOG.info("{} :: NOT FOUND", entity);
		} else if (BooleanUtils.isFalse(Hibernate.isInitialized(entity))) {
			LOG.info("{} :: NOT INITIALIZED", entity);
		} else {
			LOG.info("{} :: {}", entity);
		}
	}
}
