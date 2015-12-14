package com.pduleba.hibernate.model;

import java.util.List;

import lombok.Data;

public @Data class OrderModel extends AbstractBaseModel {

	private int totalAmount;
	
	private boolean accepted;
	
	private boolean send;
	
	private UserModel owner;
	
	private List<ProductModel> products;
}
