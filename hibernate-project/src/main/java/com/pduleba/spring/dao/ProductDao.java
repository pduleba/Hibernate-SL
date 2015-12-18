package com.pduleba.spring.dao;

import java.io.Serializable;
import java.util.List;

import com.pduleba.hibernate.model.ProductModel;

public interface ProductDao {

	List<ProductModel> getAllProducts();

	Serializable save(ProductModel product);

	void removeAll(List<ProductModel> products);

}
