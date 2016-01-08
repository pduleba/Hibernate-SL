package com.pduleba.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public @Data class CarPK implements Serializable {

	private static final long serialVersionUID = 2395097171032092217L;

	public CarPK(String name) {
		super();
		this.name = name;
	}

	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;
}
