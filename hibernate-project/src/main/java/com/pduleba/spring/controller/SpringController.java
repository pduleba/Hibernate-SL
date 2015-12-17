package com.pduleba.spring.controller;

import java.util.List;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.UserModel;

public interface SpringController {

	void saveUser(UserModel user);
	List<UserModel> getAllUsers();
	void removeUsers(List<UserModel> users);

	void saveOrder(OrderModel order);
	List<OrderModel> getAllOrders();
	void removeOrders(List<OrderModel> orders);

}
