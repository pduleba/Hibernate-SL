package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String dateId) {
		super();
		this.id = new CarPK(name);
		this.dateId = dateId;
	}

	@EmbeddedId
	private CarPK id;

	@Column(name = "DATE_ID")
	private String dateId;

}
