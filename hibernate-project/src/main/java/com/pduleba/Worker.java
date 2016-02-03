package com.pduleba;

import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;

class Worker {
	
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	void showProducts(Collection<ProductModel> products) {
		showProducts(products, true);
	}
	
	void showProducts(Collection<ProductModel> products, boolean showDelimiter) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(products))) {
			LOG.info("Products -> NOT INITIALIZED");
		} else if (Objects.isNull(products) || products.isEmpty()) {
			LOG.info("Products -> NOT FOUND");
		} else {
			int index = 0;
			for (ProductModel p : products) {
				LOG.info("#> product {} ", ++index);
				displayProduct(p);
				if (showDelimiter) {
					LOG.info("-----");
				}
			}
		}
	}

	void showOrders(Collection<OrderModel> orders) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(orders))) {
			LOG.info("Orders -> NOT INITIALIZED");
		} else if (Objects.isNull(orders) || orders.isEmpty()) {
			LOG.info("Orders -> NOT FOUND");
		} else {
			int index = 0;
			for (OrderModel o : orders) {
				LOG.info("#> order {} ", ++index);
				displayOrder(o);
				showProducts(o.getProducts(), false);
				LOG.info("-----");
			}
		}
	}

	private void displayProduct(ProductModel p) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(p))) {
			LOG.info("PRODUCT_MODEL :: NOT INITIALIZED");
		} else if (Objects.nonNull(p)) {
			LOG.info("PRODUCT_MODEL :: id = {}, name = {}", p.getId(), p.getName());
		} else {
			LOG.info("PRODUCT_MODEL :: NOT FOUND");
		}
	}

	private void displayOrder(OrderModel o) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(o))) {
			LOG.info("ORDER_MODEL :: NOT INITIALIZED");
		} else if (Objects.nonNull(o)) {
			LOG.info("ORDER_MODEL :: id = {}, order details = {}", o.getId(), o.getOrderDetails());
		} else {
			LOG.info("ORDER_MODEL :: NOT FOUND");
		}
	}

	private ProductModel getProduct(String productName) {
		ProductModel product = new ProductModel();
		product.setName(productName);
		
		return product;
	}

	private OrderModel getOrder(String orderName) {
		OrderModel order = new OrderModel();
		order.setOrderDetails(orderName);
		
		return order;
	}
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	public Pair<Collection<ProductModel>, Collection<OrderModel>> getProductsAndOrders() {
		String dateId = getDateId();
		
		ProductModel car = getProduct(getName("car", dateId));
		ProductModel bow = getProduct(getName("bow", dateId));
		ProductModel pen = getProduct(getName("pen", dateId));

		OrderModel o1 = getOrder(getName("car + bow", dateId));
		o1.getProducts().add(car);
		o1.getProducts().add(bow);
		
		OrderModel o2 = getOrder(getName("car + pen", dateId));
		o2.getProducts().add(car);
		o2.getProducts().add(pen);

		OrderModel o3 = getOrder(getName("bow + pen", dateId));
		o3.getProducts().add(bow);
		o3.getProducts().add(pen);
		
		Collection<ProductModel> products = Arrays.asList(bow, car, pen);
		Collection<OrderModel> orders = Arrays.asList(o1, o2, o3);
		
		return Pair.of(products, orders);
	}

	private String getName(String name, String dateId) {
		return MessageFormat.format("{0}-{1}", name, dateId);
	}
}
