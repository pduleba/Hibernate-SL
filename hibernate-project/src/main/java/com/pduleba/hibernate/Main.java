package com.pduleba.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
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
		
		saveUser();
		LOG.info(" ----- CREATE complete ----- ");
		List<UserModel> allUsers = getAllUsers();
		LOG.info(" ----- READ complete ----- ");
		removeAllUsers(allUsers);
		LOG.info(" ----- DELETE complete ----- ");
		getAllUsers();
		LOG.info(" ----- READ complete ----- ");
		
		LOG.info("Complete");
	}

	private void removeAllUsers(List<UserModel> users) {
		this.controller.removeAll(users);
	}

	private List<UserModel> getAllUsers() {
		int index = 0;
		List<UserModel> allUsers = this.controller.getAllUsers();
		if (allUsers.isEmpty()) {
			LOG.info("Users NOT FOUND");
		} else {
			for (UserModel u : allUsers) {
				index++;
				LOG.info("User {} :: id = {}, login = {}, password = {}", index, u.getId(), u.getLogin(), u.getPassword());
				UserDetailsModel details = u.getUserDetails();
				if (Objects.nonNull(details)) {
					LOG.info("User details {} :: id = {}, details = {}", index, details.getId(), details.getDetails());
				}
			}
		}
		
		return allUsers;
	}

	private void saveUser() {
		UserModel user = new UserModel();
		user.setLogin(generateString(128, 32));
		user.setPassword(generateString(64, 16));
		
		UserDetailsModel userDetails = new UserDetailsModel();
		userDetails.setDetails(generateString(10, 2));
		
		user.setUserDetails(userDetails);
		userDetails.setAssignedTo(user);
		
		this.controller.saveUser(user);
	}

	private String generateString(int bitsNum, int radix) {
		return new BigInteger(bitsNum, random).toString(radix);
	}
	
}
