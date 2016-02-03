package com.pduleba;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;
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
	
			LOG.info("######## PRODUCT CRUDS ######## ");
			executeProductsCRUD();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOG.error("Thread.sleep() problem :: {}", e.getMessage(), e);
			}
			LOG.info("######## ORDER CRUDS ######## ");
			executeOrdersCRUD();
		}
	}

	private void executeProductsCRUD() {
		saveProducts();
		LOG.info(" ----- CREATE complete ----- ");
		List<ProductModel> allProducts = getAllProducts();
		LOG.info(" ----- READ complete ----- ");
		removeAllProducts(allProducts);
		LOG.info(" ----- DELETE complete ----- ");
		getAllProducts();
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

	private void removeAllProducts(List<ProductModel> products) {
		this.controller.removeProducts(products);
	}

	private void removeAllOrders(List<OrderModel> orders) {
		this.controller.removeOrders(orders);
	}

	private List<ProductModel> getAllProducts() {
		List<ProductModel> allProducts = this.controller.getAllProducts();
		worker.showProducts(allProducts);

		return allProducts;
	}

	private List<OrderModel> getAllOrders() {
		List<OrderModel> allOrders = this.controller.getAllOrders();
		worker.showOrders(allOrders);

		return allOrders;
	}

	private void saveProducts() {
		Collection<ProductModel> products = worker.getProductsAndOrders().getLeft();
		this.controller.saveProducts(products);
	}

	private void saveOrder() {
		Collection<OrderModel> orders = worker.getProductsAndOrders().getRight();
		this.controller.saveOrders(orders);
	}

}
