package com.pduleba.spring.services;

import java.io.Serializable;
import java.util.List;

import com.pduleba.hibernate.model.UserModel;

public interface UserService {

	List<UserModel> getAllUsers();

	Serializable save(UserModel user);

	void removeAll(List<UserModel> users);

}
