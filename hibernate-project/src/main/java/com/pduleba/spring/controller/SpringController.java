package com.pduleba.spring.controller;

import java.util.List;

import com.pduleba.hibernate.model.UserDetailsModel;
import com.pduleba.hibernate.model.UserModel;

public interface SpringController {

	void saveUser(UserModel user);
	List<UserModel> getAllUsers();
	void removeUsers(List<UserModel> users);

	void saveUserDetails(UserDetailsModel user);
	List<UserDetailsModel> getAllUserDetailss();
	void removeUserDetailss(List<UserDetailsModel> users);

}
