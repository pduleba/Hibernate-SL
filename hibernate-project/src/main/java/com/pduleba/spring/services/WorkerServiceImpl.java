package com.pduleba.spring.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;

@Component
class WorkerServiceImpl implements WorkerService {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Override
	public void showCar(CarModel car, Mode mode) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(car))) {
			LOG.info("Car {} :: NOT INITIALIZED", mode);
		} else if (Objects.isNull(car)) {
			LOG.info("Car {} :: NOT FOUND", mode);
		} else {
			LOG.info("Car {} :: {}", car);
		}
	}
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	@Override
	public CarModel getCar() {
		return new CarModel("Audi", getDateId());
	}
}
