package com.pduleba.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.UserDetailsModel;
import com.pduleba.spring.dao.UserDetailsDao;

@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsDao userDetailsDao;

	@Override
	public void removeAll(List<UserDetailsModel> details) {
		userDetailsDao.removeAll(details);
	}
	
	@Override
	public List<UserDetailsModel> getAllUserDetailss() {
		return userDetailsDao.getAllUserDetailss();
	}
	
	@Override
	public void saveUserDetails(UserDetailsModel order) {
		userDetailsDao.saveUserDetails(order);
	}
}
