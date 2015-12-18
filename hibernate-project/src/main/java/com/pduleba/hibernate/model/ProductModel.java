package com.pduleba.hibernate.model;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_PRODUCTS")
@SequenceGenerator(name = "products-sequence-generator", sequenceName = "PRODUCTS_SEQ", initialValue = 1, allocationSize = 1)
@EqualsAndHashCode(exclude="orders")
public @Data class ProductModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "products-sequence-generator", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "name")
	private String name;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="products")
	private Collection<OrderModel> orders = new LinkedHashSet<>();
}
