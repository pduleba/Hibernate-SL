package com.pduleba.spring.services;

import java.io.Serializable;
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
	public List<UserModel> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	public Serializable save(UserModel user) {
		return userDao.save(user);
	}
}
