package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name="T_ORDERS", 
	uniqueConstraints = @UniqueConstraint(columnNames = {"ID", "ORDER_TYPE"})
)
public @Data class OrderModel {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="orders-sequence-generator", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="orders-sequence-generator", sequenceName="ORDERS_SEQ", initialValue=1, allocationSize=1)
	private Long id;
	
	@Column(name="ORDER_DETAILS")
	private String orderDetails;

	@Column(name="ORDER_TYPE")
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_user", referencedColumnName="id")
	private UserModel user;

	@Override
	public String toString() {
		return "OrderModel [id=" + id + ", user=" + user + "]";
	}	
	
}
