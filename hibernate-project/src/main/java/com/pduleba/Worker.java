package com.pduleba;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pduleba.hibernate.model.UserDetailsModel;
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
//				showUserDetailss(u.getUserDetailss(), false);
			}
		}
	}

	void showUserDetailss(Collection<UserDetailsModel> userDetailss, boolean showUsers) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(userDetailss))) {
			LOG.info("UserDetailss NOT INITIALIZED");
		} else if (Objects.isNull(userDetailss) || userDetailss.isEmpty()) {
			LOG.info("UserDetailss NOT FOUND");
		} else {
			int userDetailsIndex = 0;
			if (Hibernate.isInitialized(userDetailss)) {
				for (UserDetailsModel o : userDetailss) {
					++userDetailsIndex;
					LOG.info("#> userDetails index {}", userDetailsIndex);
					LOG.info("USER_DETAILS_MODEL :: id = {}, details = {}", o.getId(), o.getUserDetails());
					if (showUsers) {
//						displayUser(userDetailsIndex, o.getOwner(), false);
					}
				}
			} else {
				LOG.info("UserDetailss not initalized");
			}
		}
	}

	private void displayUser(int userIndex, UserModel u, boolean showIndex) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(u))) {
			LOG.info("UserDetailss NOT INITIALIZED");
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