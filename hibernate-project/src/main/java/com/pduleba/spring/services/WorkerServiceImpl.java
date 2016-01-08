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

import com.pduleba.hibernate.model.AbstractCarModel;
import com.pduleba.hibernate.model.CarModel;
import com.pduleba.hibernate.model.LargeCarModel;
import com.pduleba.hibernate.model.SmallCarModel;
import com.pduleba.hibernate.model.SportCarModel;

@Component
class WorkerServiceImpl implements WorkerService {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Override
	public void showCars(Collection<AbstractCarModel> cars) {
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
	public Collection<AbstractCarModel> getCars() {
		String dateId = getDateId();

		AbstractCarModel c1 = new SportCarModel("Porshe", dateId);
		AbstractCarModel c2 = new SmallCarModel("G-Wiz", "wheel", dateId);
		AbstractCarModel c3 = new LargeCarModel("Autosan", 10L, 20L, dateId);

		return Arrays.asList(c1, c2, c3);
	}
}
