package com.pduleba.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;
import com.pduleba.spring.services.CategoryService;
import com.pduleba.spring.services.OrderService;
import com.pduleba.spring.services.ProductService;
import com.pduleba.spring.services.UserDetailsService;
import com.pduleba.spring.services.UserService;

@Component
public class SpringControllerImpl implements SpringController {

	public static final Logger LOG = LoggerFactory.getLogger(SpringControllerImpl.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;
	
	@Override
	public void saveProduct(ProductModel product) {
		productService.save(product);
	}
	
	@Override
	public List<ProductModel> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@Override
	public void removeProducts(List<ProductModel> products) {
		if (deleteEnabled) {
			productService.removeAll(products);
		}
	}

	@Override
	public void saveOrder(OrderModel order) {
		orderService.saveOrder(order);
	}

	@Override
	public List<OrderModel> getAllOrders() {
		return orderService.getAllOrders();
	}

	@Override
	public void removeOrders(List<OrderModel> orders) {
		if (deleteEnabled) {
			this.orderService.removeAll(orders);
		}
	}
	
	
}
