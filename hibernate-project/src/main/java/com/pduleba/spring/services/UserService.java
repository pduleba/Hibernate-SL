package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.UserModel;

public interface UserService {

	void saveUsers(Collection<UserModel> users);

	List<UserModel> getAllUsers();

	void removeAll(List<UserModel> users);

}
