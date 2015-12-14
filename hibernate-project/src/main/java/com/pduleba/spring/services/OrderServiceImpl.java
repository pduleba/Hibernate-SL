package com.pduleba.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pduleba.spring.dao.OrderDao;

@Repository
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
}
