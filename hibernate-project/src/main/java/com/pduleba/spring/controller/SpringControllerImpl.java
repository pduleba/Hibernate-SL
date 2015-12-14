package com.pduleba.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pduleba.spring.services.CategoryService;
import com.pduleba.spring.services.OrderService;
import com.pduleba.spring.services.ProductService;
import com.pduleba.spring.services.RoleService;
import com.pduleba.spring.services.UserService;

@Component
public class SpringControllerImpl implements SpringController {

	public static final Logger LOG = LoggerFactory.getLogger(SpringControllerImpl.class);
	
	private RoleService roleService;
	private UserService userService;
	private OrderService orderService;
	private ProductService productService;
	private CategoryService categoryService;
	
	@Override
	public void doAction() {
		LOG.info("Action complete.");
	}
}
