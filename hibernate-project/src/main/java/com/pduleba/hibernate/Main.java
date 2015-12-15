package com.pduleba.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.UserDetailsModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.controller.SpringController;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private SecureRandom random = new SecureRandom();
	private SpringController controller;
	
	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		this.controller = ctx.getBean(SpringController.class);
	}
	
	private void run() {
		LOG.info("Starting...");
		
		UserModel user = new UserModel();
		user.setLogin(generateString(32));
		user.setPassword(generateString(16));
		
		UserDetailsModel userDetails = new UserDetailsModel();
		userDetails.setDetails(generateString(8));
		
		user.setUserDetails(userDetails);
		userDetails.setAssignedTo(user);
		
		this.controller.saveUser(user);
		LOG.info("Saved!");
		int index = 0;
		for (UserModel u : this.controller.getAllUsers()) {
			index++;
			LOG.info("User {} :: id = {}, name = {}", index, u.getId(), u.getName());
			UserDetailsModel details = u.getUserDetails();
			if (Objects.nonNull(details)) {
				LOG.info("User details {} :: id = {}, details = {}", index, details.getId(), details.getDetails());
			}
		}
		
		LOG.info("Complete");
	}

	private String generateString(int radix) {
		return new BigInteger(130, random).toString(radix);
	}
	
}
