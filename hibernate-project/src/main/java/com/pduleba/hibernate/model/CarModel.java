package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String dateId) {
		super();
		this.name = name;
		this.dateId = dateId;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "car-table-generator", strategy = GenerationType.SEQUENCE)
	@TableGenerator(name = "car-table-generator", table = "T_ID_GENERATOR", pkColumnName = "ID_GENERATOR", 
		pkColumnValue = "T_CAR_GENERATOR", valueColumnName = "COUNT", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;

}
