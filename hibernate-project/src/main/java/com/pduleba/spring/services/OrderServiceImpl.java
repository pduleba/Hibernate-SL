package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.spring.dao.OrderDao;

@Repository
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public void removeAll(List<OrderModel> orders) {
		orderDao.removeAll(orders);
	}
	
	@Override
	public List<OrderModel> getAllOrders() {
		return orderDao.getAllOrders();
	}
	
	@Override
	public void saveOrders(Collection<OrderModel> orders) {
		orderDao.saveOrders(orders);
	}
}
