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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_ORDERS")
@SequenceGenerator(name = "orders-sequence-generator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
@EqualsAndHashCode(exclude="products")
public @Data class OrderModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "orders-sequence-generator", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "order_details")
	private String orderDetails;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
		name = "t_order2product", 
		joinColumns = { @JoinColumn(name = "id_order", referencedColumnName = "id") }, 
		inverseJoinColumns = { @JoinColumn(name = "id_product", referencedColumnName = "id") })
	private Collection<ProductModel> products = new LinkedHashSet<>();
}
