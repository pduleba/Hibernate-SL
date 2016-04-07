package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.User;

public interface UserDao {
    
    void addUser(User user);
     
    void deleteUser(User user);
     
    List<User> getAllUsers();
 
    User getUserById(long id);
    
    void updateUser(User user);

	void deleteAllUsers();
	
	User getUserByUsername(String username);
	
	List<User> getUsersByRole(String role);
	
	void deactivateUserAccount(long id,boolean value);
	
	List<User> checkUniqueNickname(String nickname);
	
	List<User> checkUniqueLogin(String login);

}
