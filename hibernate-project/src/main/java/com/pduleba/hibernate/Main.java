package com.pduleba.hibernate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.controller.SpringController;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private SpringController controller;
	private Worker worker = new Worker();

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
	}

	private void run() {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			
			LOG.info("Starting...");
	
			LOG.info("######## USER CRUDS ######## ");
			executeUsersCRUD();
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

	private void removeAllUsers(List<UserModel> users) {
		this.controller.removeUsers(users);
	}

	private List<UserModel> getAllUsers() {
		List<UserModel> allUsers = this.controller.getAllUsers();
		worker.showUsers(allUsers);

		return allUsers;
	}

	private void saveUser() {
		UserModel user = new UserModel();
		user.setName(worker.generateString(16, 16));

		OrderModel order = null;
		for (int i = 0; i < 3; i++) {
			order = new OrderModel();
			order.setOrderDetails(worker.generateString(10, 2));
			user.getOrders().add(order);
		}

		this.controller.saveUser(user);
	}
}
