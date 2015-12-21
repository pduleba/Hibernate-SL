package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.ProductModel;

public interface ProductService {

	public void saveAll(Collection<ProductModel> products);

	List<ProductModel> getAllProducts();

	void removeAll(List<ProductModel> products);

}
