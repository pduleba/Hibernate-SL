package com.pduleba.spring.services;

import static com.pduleba.hibernate.model.CarType.BIG;
import static com.pduleba.hibernate.model.CarType.SMALL;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.CarModel;

@Component
class WorkerServiceImpl implements WorkerService {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	@Override
	public CarModel getCar() {
		return new CarModel("Audi", getDateId(), Math.random() > 0.5 ? BIG : SMALL);
	}
}
