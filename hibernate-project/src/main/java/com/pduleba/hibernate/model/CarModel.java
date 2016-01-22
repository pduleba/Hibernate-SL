package com.pduleba.hibernate.model;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
public @Data class CarModel {

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Column(name = "NAME")
	private String name;

	@Column(name = "CREATED_IN")
	private Date createdIn;
	
	@Column(name = "WHEELS_NUMBER")
	private Integer wheelsNumber;
	
	@Lob
	@Column(name = "DOCUMENTATION")
	private Clob documentation;
	
	@Lob
	@Column(name = "IMAGE")
	private Blob image;
	
	@OneToMany(mappedBy = "ownedCar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OwnerModel> owners;
}
