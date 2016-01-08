package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public @Data class LocationModel {

	@Column(name = "X_LOCATION")
	private Integer xLocation;

	@Column(name = "Y_LOCATION")
	private Integer yLocation;

}
