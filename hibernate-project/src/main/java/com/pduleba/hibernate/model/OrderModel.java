package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="T_ORDERS")
@SequenceGenerator(name="orders-sequence-generator", sequenceName="ORDERS_SEQ", initialValue=1, allocationSize=1)
@EqualsAndHashCode(exclude="user")
public @Data class OrderModel {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="orders-sequence-generator", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="order_details")
	private String orderDetails;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="t_user2orders", 
			joinColumns = {@JoinColumn(name="id_order")},
			inverseJoinColumns = {@JoinColumn(name="id_user")}
		)
	private UserModel user;

}