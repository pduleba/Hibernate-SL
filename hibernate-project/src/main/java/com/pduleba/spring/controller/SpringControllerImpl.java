package com.pduleba.spring.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.services.QuestionService;
import com.pduleba.spring.services.UserService;

@Component
public class SpringControllerImpl implements SpringController {

	public static final Logger LOG = LoggerFactory.getLogger(SpringControllerImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;
	
	@Value(value="${application.remove.enabled}")
	private boolean deleteEnabled = true;
	
	@Override
	public void saveQuestions(Collection<QuestionModel> questions) {
		questionService.saveAll(questions);
	}
	
	@Override
	public List<QuestionModel> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@Override
	public void removeQuestions(List<QuestionModel> questions) {
		if (deleteEnabled) {
			questionService.removeAll(questions);
		}
	}

	@Override
	public void saveUsers(Collection<UserModel> users) {
		userService.saveUsers(users);
	}

	@Override
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}

	@Override
	public void removeUsers(List<UserModel> users) {
		if (deleteEnabled) {
			this.userService.removeAll(users);
		}
	}
	
	
}
