package com.pduleba.hibernate.model;

import java.sql.Clob;
import java.sql.NClob;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	public CarModel(String name, String dateId, Clob clob, NClob nclob) {
		super();
		this.name = name;
		this.dateId = dateId;
		this.clob = clob;
		this.nclob = nclob;
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
	@Column(name = "CLOB_COLUMN")
	private Clob clob;
	
	@Lob
	@Column(name = "NCLOB_COLUMN")
	private NClob nclob;

}
