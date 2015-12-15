package com.pduleba.hibernate.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

public @Data class CategoryModel {

	private Long id;

	private Date createDate;
	
	private String name;

	private List<ProductModel> assignedTo;
}
