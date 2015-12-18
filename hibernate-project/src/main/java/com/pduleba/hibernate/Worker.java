package com.pduleba.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.ProductModel;

class Worker {
	
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);

	private SecureRandom random = new SecureRandom();
	
	void showProducts(Collection<ProductModel> products) {
		showProducts(products, true);
	}	
	
	void showProducts(Collection<ProductModel> products, boolean showOrders) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(products))) {
			LOG.info("Products -> NOT INITIALIZED");
		} else if (Objects.isNull(products) || products.isEmpty()) {
			LOG.info("Products -> NOT FOUND");
		} else {
			int index = 0;
			for (ProductModel p : products) {
				LOG.info("#> product {} ", ++index);
				displayProduct(p);
				if (showOrders) {
					showOrders(p.getOrders(), false);
				}
				LOG.info("-----");
			}
		}
	}
	void showOrders(Collection<OrderModel> orders) {
		showOrders(orders, true);
	}

	void showOrders(Collection<OrderModel> orders, boolean showProducts) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(orders))) {
			LOG.info("Orders -> NOT INITIALIZED");
		} else if (Objects.isNull(orders) || orders.isEmpty()) {
			LOG.info("Orders -> NOT FOUND");
		} else {
			int index = 0;
			for (OrderModel o : orders) {
				LOG.info("#> order {} ", ++index);
				displayOrder(o);
				if (showProducts) {
					showProducts(o.getProducts(), false);
				}
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

	String generateString(int bitsNum, int radix) {
		return new BigInteger(bitsNum, random).toString(radix);
	}

}