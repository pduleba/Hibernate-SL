package com.pduleba.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.UserModel;
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
	
	@Override
	public void saveUser(UserModel user) {
		userService.save(user);
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}
}
