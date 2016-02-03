package com.pduleba.spring.services;

import java.util.List;

import com.pduleba.hibernate.model.UserDetailsModel;

public interface UserDetailsService {

	void removeAll(List<UserDetailsModel> details);

	List<UserDetailsModel> getAllUserDetailss();

	void saveUserDetails(UserDetailsModel details);

}
