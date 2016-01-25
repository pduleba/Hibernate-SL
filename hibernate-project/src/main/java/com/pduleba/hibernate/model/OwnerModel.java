package com.pduleba.hibernate.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_OWNER")
@NoArgsConstructor
public @Data class OwnerModel {

	public OwnerModel(String firstName, String lastName, Integer age, OwnerType type) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.since = new Date(System.currentTimeMillis());
		this.age = age;
	}

	@Id
	@GeneratedValue(generator = "owner-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "owner-sequence-generator", sequenceName = "OWNER_SEQ", initialValue = 1, allocationSize = 20)
	private Long id;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private OwnerType type;
	
	@Column(name = "SINCE")
	@Temporal(TemporalType.TIME)
	private Date since;
	
	@Column(name = "AGE")
	private Integer age;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = CarModel.class)
	private List<CarModel> cars = new LinkedList<>();

	public void addCar(CarModel car) {
		cars.add(car);
		car.setOwner(this);
	}
}
