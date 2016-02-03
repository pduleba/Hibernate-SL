package com.pduleba;

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
	public static final boolean DELETE_ENABLED = false;
	
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
			LOG.info("######## ORDER CRUDS ######## ");
			executeOrdersCRUD();
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

	private void executeOrdersCRUD() {
		saveOrder();
		LOG.info(" ----- CREATE complete ----- ");
		List<OrderModel> allOrders = getAllOrders();
		LOG.info(" ----- READ complete ----- ");
		removeAllOrders(allOrders);
		LOG.info(" ----- DELETE complete ----- ");
		getAllOrders();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void removeAllUsers(List<UserModel> users) {
		if (DELETE_ENABLED) {
			this.controller.removeUsers(users);
		}
	}

	private void removeAllOrders(List<OrderModel> orders) {
		if (DELETE_ENABLED) {
			this.controller.removeOrders(orders);
		}
	}

	private List<UserModel> getAllUsers() {
		List<UserModel> allUsers = this.controller.getAllUsers();
		worker.showUsers(allUsers);

		return allUsers;
	}

	private List<OrderModel> getAllOrders() {
		List<OrderModel> allOrders = this.controller.getAllOrders();
		worker.showOrders(allOrders, true);

		return allOrders;
	}

	private void saveUser() {
		UserModel user = new UserModel();
		user.setName(worker.generateString(16, 16));

		OrderModel order = null;
		for (int i = 0; i < 3; i++) {
			order = new OrderModel();
			order.setOrderDetails(worker.generateString(10, 2));
//			order.setOwner(user);
			user.getOrders().add(order);
		}

		this.controller.saveUser(user);
	}

	private void saveOrder() {
		UserModel user = new UserModel();
		user.setName(worker.generateString(16, 16));

		OrderModel order = new OrderModel();
		order.setOrderDetails(worker.generateString(10, 2));
//		order.setOwner(user);
		user.getOrders().add(order);

		this.controller.saveOrder(order);
	}

}
