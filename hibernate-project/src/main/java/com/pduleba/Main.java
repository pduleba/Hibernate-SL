package com.pduleba;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
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
		new Main().execute();
	}

	public void execute() {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			
			run();
		}
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
				LOG.info("User {} :: id = {}, name = {}", index, u.getId(), u.getName());
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
		user.setName(generateString(16, 16));
		
		UserDetailsModel userDetails = new UserDetailsModel();
		userDetails.setDetails(generateString(10, 2));
		
		user.setUserDetails(userDetails);
		
		this.controller.saveUser(user);
	}

	private String generateString(int bitsNum, int radix) {
		return new BigInteger(bitsNum, random).toString(radix);
	}
	
}
