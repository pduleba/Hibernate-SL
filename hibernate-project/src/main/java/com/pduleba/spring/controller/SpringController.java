package com.pduleba.spring.controller;

import java.util.List;

import com.pduleba.hibernate.model.UserModel;

public interface SpringController {

	void saveUser(UserModel user);

	List<UserModel> getAllUsers();

}
