package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.ProductModel;

public interface ProductDao {

	List<ProductModel> getAllProducts();

	public void saveAll(Collection<ProductModel> products);

	void removeAll(List<ProductModel> products);

}
