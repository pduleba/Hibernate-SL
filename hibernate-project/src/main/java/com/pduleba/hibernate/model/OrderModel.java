package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public @Data class OrderModel {
	
	@Column(name="order_details")
	private String orderDetails;

}
