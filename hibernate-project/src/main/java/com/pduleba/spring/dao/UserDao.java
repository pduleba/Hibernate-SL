package com.pduleba.spring.dao;

import java.io.Serializable;
import java.util.List;

import com.pduleba.hibernate.model.UserModel;

public interface UserDao {

	List<UserModel> getAllUsers();

	Serializable save(UserModel user);

	void removeAll(List<UserModel> users);
	
}
