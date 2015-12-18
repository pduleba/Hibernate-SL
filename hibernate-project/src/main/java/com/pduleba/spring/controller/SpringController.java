package com.pduleba.spring.controller;

import java.util.List;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;

public interface SpringController {

	void saveProduct(ProductModel product);
	List<ProductModel> getAllProducts();
	void removeProducts(List<ProductModel> products);

	void saveOrder(OrderModel order);
	List<OrderModel> getAllOrders();
	void removeOrders(List<OrderModel> orders);

}
