package com.pduleba.hibernate.model;

import java.util.List;

import lombok.Data;

public @Data class CategoryModel extends AbstractBaseModel {

	private String name;

	private List<ProductModel> assignedTo;
}
