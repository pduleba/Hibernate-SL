package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@IdClass(value = CarPK.class) // <- Composite Key Declaration
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String dateId) {
		super();
		this.name = name;
		this.dateId = dateId;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator="car-id-generator",  strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-id-generator", sequenceName="CAR_SEQ", initialValue=1, allocationSize=1)
	private Long id;

	@Id
	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;

}
