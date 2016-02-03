package com.pduleba.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.UserDetailsModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.services.CategoryService;
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
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;
	
	@Override
	public void saveUser(UserModel user) {
		userService.save(user);
	}
	
	@Override
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@Override
	public void removeUsers(List<UserModel> users) {
		if (deleteEnabled) {
			userService.removeAll(users);
		}
	}

	@Override
	public void saveUserDetails(UserDetailsModel user) {
		userDetailsService.saveUserDetails(user);
	}

	@Override
	public List<UserDetailsModel> getAllUserDetailss() {
		return userDetailsService.getAllUserDetailss();
	}

	@Override
	public void removeUserDetailss(List<UserDetailsModel> users) {
		if (deleteEnabled) {
			this.userDetailsService.removeAll(users);
		}
	}
	
	
}
