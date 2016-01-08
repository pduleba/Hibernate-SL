package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass					// Inheritance strategy type definition (inheritance is ONLY on class level - not in DB lavel)
@NoArgsConstructor
public @Data abstract class AbstractCarModel {

	public AbstractCarModel(String baseName, String dateId) {
		super();
		this.baseName = baseName;
		this.dateId = dateId;
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "BASE_NAME")
	private String baseName;
	
	@Column(name = "DATE_ID")
	private String dateId;

}
