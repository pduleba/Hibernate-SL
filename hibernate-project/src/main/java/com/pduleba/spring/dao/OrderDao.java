package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.OrderModel;

public interface OrderDao {

	void removeAll(List<OrderModel> orders);

	List<OrderModel> getAllOrders();

	void saveOrders(Collection<OrderModel> orders);

}
