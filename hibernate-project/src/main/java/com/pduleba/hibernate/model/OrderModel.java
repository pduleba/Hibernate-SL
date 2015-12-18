package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="T_ORDERS")
@SequenceGenerator(name="orders-sequence-generator", sequenceName="ORDERS_SEQ", initialValue=1, allocationSize=1)
@SecondaryTable(name = "t_user2orders", 
	pkJoinColumns={ @PrimaryKeyJoinColumn(name="id_order", referencedColumnName="id") },
	uniqueConstraints={ @UniqueConstraint(columnNames="id_user") }
)
@EqualsAndHashCode(exclude="user")
public @Data class OrderModel {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="orders-sequence-generator", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="order_details")
	private String orderDetails;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(table="t_user2orders", name="id_user", referencedColumnName="id")
	private UserModel user;

}
