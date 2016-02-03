package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_PRODUCTS")
@SequenceGenerator(name = "products-sequence-generator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
public @Data class ProductModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "products-sequence-generator", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "name")
	private String name;
}
