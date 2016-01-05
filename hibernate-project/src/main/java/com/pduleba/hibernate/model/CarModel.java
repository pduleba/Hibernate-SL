package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@SecondaryTables(value = {
		@SecondaryTable(name = "T_CAR_COLOR", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "ID_COLOR", referencedColumnName = "ID")}),		
		@SecondaryTable(name = "T_CAR_ENGINE", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "ID_ENGINE", referencedColumnName = "ID")}),		
		@SecondaryTable(name = "T_CAR_TYPE", pkJoinColumns = {@PrimaryKeyJoinColumn(name = "ID_TYPE", referencedColumnName = "ID")})
})
@NoArgsConstructor
public @Data class CarModel {

	public CarModel(String name, String color, String engine, String type, String dateId) {
		super();
		this.name = name;
		this.color = color;
		this.engine = engine;
		this.type = type;
		
		this.dateId = dateId;
		this.dateIdOfColor = dateId;
		this.dateIdOfEngine = dateId;
		this.dateIdOfType = dateId;
	}

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "COLOR", table = "T_CAR_COLOR")
	private String color;
	
	@Column(name = "ENGINE", table = "T_CAR_ENGINE")
	private String engine;
	
	@Column(name = "TYPE", table = "T_CAR_TYPE")
	private String type;
	
	@Column(name = "DATE_ID")
	private String dateId;
	
	@Column(name = "DATE_ID", table = "T_CAR_COLOR")
	private String dateIdOfColor;
	
	@Column(name = "DATE_ID", table = "T_CAR_ENGINE")
	private String dateIdOfEngine;
	
	@Column(name = "DATE_ID", table = "T_CAR_TYPE")
	private String dateIdOfType;

}
