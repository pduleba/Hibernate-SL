package com.pduleba.hibernate.model;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String dateId, Blob image, Clob documentation) {
		super();
		this.name = name;
		this.dateId = dateId;
		this.image = image;
		this.documentation = documentation;
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;

	@Lob 
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "IMAGE")
	private Blob image;
	
	@Lob 
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "documentation")
	private Clob documentation;
}
