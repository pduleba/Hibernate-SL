package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.ProductModel;

public interface ProductService {

	void save(ProductModel product);

	List<ProductModel> getAllProducts();

	void removeAll(List<ProductModel> products);

}
