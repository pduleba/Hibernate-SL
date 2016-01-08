package com.pduleba.hibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, EngineModel engine, String dateId) {
		super();
		this.name = name;
		this.dateId = dateId;
		this.engine = engine;
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;
	
	@Embedded
	@AttributeOverrides({ 
		@AttributeOverride(name = "type", column = @Column(name = "ENGINE_TYPE") ),				// <-- Sharing and overriding
		@AttributeOverride(name = "size", column = @Column(name = "ENGINE_SIZE") ) 				// <-- Sharing and overriding
	})
	private EngineModel engine;

}
