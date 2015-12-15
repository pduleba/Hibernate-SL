package com.pduleba.hibernate.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

public @Data class ProductModel {

	private Long id;

	private Date createDate;

	private String name;
	
	private CategoryModel category;
	
	private List<OrderModel> assignedTo;
}
