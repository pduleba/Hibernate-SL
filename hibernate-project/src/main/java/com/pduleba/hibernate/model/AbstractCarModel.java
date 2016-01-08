package com.pduleba.hibernate.model;

import static javax.persistence.DiscriminatorType.STRING;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR_SINGLE_TABLE_STRATEGY")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)												// Inheritance strategy type definition
@DiscriminatorColumn(discriminatorType = STRING, name = "CLASS_NAME_DISCRIMINATOR", length = 100) 	// Inheritance discriminator column definition
@DiscriminatorOptions(force = true)																	// Force discriminator value definition on sub-classess
public @Data abstract class AbstractCarModel implements CarModel {

	public AbstractCarModel(String name, String dateId) {
		super();
		this.name = name;
		this.dateId = dateId;
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
