package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void saveUsers(Collection<UserModel> users) {
		userDao.saveAll(users);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void removeAll(List<UserModel> users) {
		userDao.removeAll(users);
	}
	
	
}
