package com.pduleba.spring.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.hibernate.model.EngineModel;
import com.pduleba.hibernate.model.LocationModel;

@Component
class WorkerServiceImpl implements WorkerService {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Override
	public void showCars(Collection<CarModel> cars) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(cars))) {
			LOG.info("Cars -> NOT INITIALIZED");
		} else if (Objects.isNull(cars) || cars.isEmpty()) {
			LOG.info("Cars -> NOT FOUND");
		} else {
			int index = 0;
			for (CarModel car : cars) {
				LOG.info("#> car {} :: {}", ++index, car);
			}
			LOG.info("-----");
		}
	}
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	@Override
	public Collection<CarModel> getCars() {
		String dateId = getDateId();

		EngineModel e = new EngineModel("gasoline", 1);
		LocationModel l = new LocationModel(90, 90);
		CarModel c = new CarModel("Audi", e, l, dateId);

		return Arrays.asList(c);
	}
}
