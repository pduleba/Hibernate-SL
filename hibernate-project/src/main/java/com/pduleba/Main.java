package com.pduleba;

import java.util.List;

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
	
	private SpringController controller;
	private Worker worker = new Worker();

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			
			LOG.info("Starting...");
	
			LOG.info("######## USER CRUDS ######## ");
			executeUsersCRUD();
			LOG.info("######## USER DETAILS CRUDS ######## ");
			executeUserDetailssCRUD();
		}
	}

	private void executeUsersCRUD() {
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

	private void executeUserDetailssCRUD() {
		saveUserDetails();
		LOG.info(" ----- CREATE complete ----- ");
		List<UserDetailsModel> allUserDetailss = getAllUserDetailss();
		LOG.info(" ----- READ complete ----- ");
		removeAllUserDetailss(allUserDetailss);
		LOG.info(" ----- DELETE complete ----- ");
		getAllUserDetailss();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void removeAllUsers(List<UserModel> users) {
		this.controller.removeUsers(users);
	}

	private void removeAllUserDetailss(List<UserDetailsModel> userDetailss) {
		this.controller.removeUserDetailss(userDetailss);
	}

	private List<UserModel> getAllUsers() {
		List<UserModel> allUsers = this.controller.getAllUsers();
		worker.showUsers(allUsers);

		return allUsers;
	}

	private List<UserDetailsModel> getAllUserDetailss() {
		List<UserDetailsModel> allUserDetailss = this.controller.getAllUserDetailss();
		worker.showUserDetailss(allUserDetailss, true);

		return allUserDetailss;
	}

	private void saveUser() {
		UserModel user = new UserModel();
		user.setName(worker.generateString(16, 16));

		UserDetailsModel userDetails = null;
		for (int i = 0; i < 3; i++) {
			userDetails = new UserDetailsModel();
			userDetails.setUserDetails(worker.generateString(10, 2));
			userDetails.setUser(user);
//			user.getUserDetailss().add(userDetails);
		}

		this.controller.saveUser(user);
	}

	private void saveUserDetails() {
		UserModel user = new UserModel();
		user.setName(worker.generateString(16, 16));

		UserDetailsModel userDetails = new UserDetailsModel();
		userDetails.setUserDetails(worker.generateString(10, 2));
		userDetails.setUser(user);
//		user.getUserDetailss().add(userDetails);

		this.controller.saveUserDetails(userDetails);
	}

}
