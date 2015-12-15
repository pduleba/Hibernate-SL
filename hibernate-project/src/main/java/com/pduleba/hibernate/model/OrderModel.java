package com.pduleba.hibernate.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

public @Data class OrderModel {

	private Long id;

	private Date createDate;

	private int totalAmount;
	
	private boolean accepted;
	
	private boolean send;
	
	private UserModel owner;
	
	private List<ProductModel> products;
}
