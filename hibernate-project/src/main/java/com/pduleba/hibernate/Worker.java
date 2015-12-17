package com.pduleba.hibernate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.model.OrderModel;
import com.pduleba.hibernate.model.UserModel;

class Worker {
	
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);

	private SecureRandom random = new SecureRandom();
	
	void showUsers(List<UserModel> allUsers) {
		int index = 0;

		if (Objects.isNull(allUsers) || allUsers.isEmpty()) {
			LOG.info("Users NOT FOUND");
		} else {
			for (UserModel u : allUsers) {
				displayUser(++index, u);
				showOrders(u.getOrders(), false);
			}
		}
	}

	void showOrders(Collection<OrderModel> orders, boolean showUsers) {
		if (Objects.isNull(orders) || orders.isEmpty()) {
			LOG.info("Orders NOT FOUND");
		} else {
			int orderIndex = 0;
			if (Hibernate.isInitialized(orders)) {
				for (OrderModel o : orders) {
					++orderIndex;
					
					if (showUsers) {
						displayUser(orderIndex, o.getOwner());
					}
					LOG.info("Order details {} :: id = {}, details = {}", orderIndex, o.getId(), o.getOrderDetails());
				}
			} else {
				LOG.info("Orders not initalized");
			}
		}
	}

	private void displayUser(int index, UserModel u) {
		if (Objects.nonNull(u)) {
			LOG.info("User {} :: id = {}, name = {}", ++index, u.getId(), u.getName());
		} else {
			LOG.info("User NOT FOUND");
		}
	}

	String generateString(int bitsNum, int radix) {
		return new BigInteger(bitsNum, random).toString(radix);
	}

}