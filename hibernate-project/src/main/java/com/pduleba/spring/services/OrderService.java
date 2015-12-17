package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.OrderModel;

public interface OrderService {

	void removeAll(List<OrderModel> orders);

	List<OrderModel> getAllOrders();

	void saveOrder(OrderModel order);

}
