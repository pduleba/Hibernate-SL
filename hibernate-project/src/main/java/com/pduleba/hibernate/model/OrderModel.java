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
@Table(name="T_ORDERS")
@SequenceGenerator(name="orders-sequence-generator", sequenceName="ORDERS_SEQ", initialValue=1, allocationSize=1)
public @Data class OrderModel {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="orders-sequence-generator", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="order_details")
	private String orderDetails;

}
