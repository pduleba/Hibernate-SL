package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.User;

public interface UserService {

	User getUserById(long id);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(User user);

	List<User> getAllUsers();
	
	User getUserByUsername(String username);
	
	List<User> getUsersByRole(String role);
	
	void deactivateUserAccount(long id,boolean value);
	
	boolean checkUniqueLogin (String login);
	
	boolean checkUniqueNickname (String nickname);
}
