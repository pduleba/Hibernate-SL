package com.pduleba.hibernate.model;

import java.util.List;

import lombok.Data;

public @Data class ProductModel extends AbstractBaseModel {

	private String name;
	
	private CategoryModel category;
	
	private List<OrderModel> assignedTo;
}
