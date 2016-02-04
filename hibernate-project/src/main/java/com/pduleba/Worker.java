package com.pduleba;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.model.UserModel;

class Worker {
	
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);

	private SecureRandom random = new SecureRandom();
	
	void showUsers(List<UserModel> allUsers) {
		if (Objects.isNull(allUsers) || allUsers.isEmpty()) {
			LOG.info("Users NOT FOUND");
		} else {
			int userIndex = 0;

			for (UserModel u : allUsers) {
				displayUser(++userIndex, u, true);
				showOrders(u.getOrders(), false);
			}
		}
	}

	void showOrders(List<?> orders, boolean showUsers) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(orders))) {
			LOG.info("Orders NOT INITIALIZED");
		} else if (Objects.isNull(orders) || orders.isEmpty()) {
			LOG.info("Orders NOT FOUND");
		} else {
			int orderIndex = 0;
			if (Hibernate.isInitialized(orders)) {
				for (Object o : orders) {
					++orderIndex;
					LOG.info("#> order index {}", orderIndex);
					LOG.info("ORDER_MODEL :: order = {}", o);
				}
			} else {
				LOG.info("Orders not initalized");
			}
		}
	}
	
	void showOrders(Map<?, ?> orders, boolean showUsers) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(orders))) {
			LOG.info("Orders NOT INITIALIZED");
		} else if (Objects.isNull(orders) || orders.isEmpty()) {
			LOG.info("Orders NOT FOUND");
		} else {
			int orderIndex = 0;
			if (Hibernate.isInitialized(orders)) {
				for (Entry<? ,?> e : orders.entrySet()) {
					++orderIndex;
					LOG.info("#> order index {}", orderIndex);
					LOG.info("ORDER_MODEL :: key = {}, value = {}", e.getKey(), e.getValue());
					if (showUsers) {
//						displayUser(orderIndex, o.getOwner(), false);
					}
				}
			} else {
				LOG.info("Orders not initalized");
			}
		}
	}

	private void displayUser(int userIndex, UserModel u, boolean showIndex) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(u))) {
			LOG.info("Orders NOT INITIALIZED");
		} else if (Objects.nonNull(u)) {
			if (showIndex) {
				LOG.info("# user index {}", userIndex);
			} 
			LOG.info(" USER_MODEL :: id = {}, name = {}", u.getId(), u.getName());
		} else {
			LOG.info("User NOT FOUND");
		}
	}

	String generateString(int bitsNum, int radix) {
		return new BigInteger(bitsNum, random).toString(radix);
	}

}