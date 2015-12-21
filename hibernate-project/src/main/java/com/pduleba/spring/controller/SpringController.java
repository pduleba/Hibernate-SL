package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;

public interface SpringController {

	void saveProducts(Collection<ProductModel> products);
	List<ProductModel> getAllProducts();
	void removeProducts(List<ProductModel> products);

	void saveOrders(Collection<OrderModel> orders);
	List<OrderModel> getAllOrders();
	void removeOrders(List<OrderModel> orders);

}
