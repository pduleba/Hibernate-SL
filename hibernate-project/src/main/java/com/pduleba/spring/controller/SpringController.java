package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;

public interface SpringController {

	void saveQuestions(Collection<QuestionModel> questions);
	List<QuestionModel> getAllQuestions();
	void removeQuestions(List<QuestionModel> questions);

	void saveUsers(Collection<UserModel> users);
	List<UserModel> getAllUsers();
	void removeUsers(List<UserModel> users);

}
