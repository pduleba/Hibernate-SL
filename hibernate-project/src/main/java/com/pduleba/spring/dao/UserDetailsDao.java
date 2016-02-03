package com.pduleba.spring.dao;

import java.util.List;

import com.pduleba.hibernate.model.UserDetailsModel;

public interface UserDetailsDao {

	void removeAll(List<UserDetailsModel> users);

	List<UserDetailsModel> getAllUserDetailss();

	void saveUserDetails(UserDetailsModel user);

}
