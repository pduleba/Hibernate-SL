package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.UserModel;

public interface UserDao {

	List<UserModel> getAllUsers();

	void saveAll(Collection<UserModel> users);

	void removeAll(List<UserModel> users);
}
