package com.pduleba.hibernate.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String dateId, Calendar creationDate) {
		super();
		this.name = name;
		this.dateId = dateId;
		this.creationDate = creationDate;
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;
	
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)				//	<-- This maps java.util.* to proper DB type
	private Calendar creationDate;					//	<-- Here we are using java.util.Calendar
	
}
