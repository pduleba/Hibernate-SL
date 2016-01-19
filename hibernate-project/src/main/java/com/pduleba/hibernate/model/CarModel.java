package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.listeners.BasicEventListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@EntityListeners(value = { BasicEventListener.class })
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public static final Logger LOG = LoggerFactory.getLogger(CarModel.class);
	
	public CarModel(String name, String dateId) {
		super();
		this.name = name;
		this.dateId = dateId;
	}
	
	@PrePersist
	public void prePersist() {
		LOG.debug("PrePersist Event {}");
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;
}
