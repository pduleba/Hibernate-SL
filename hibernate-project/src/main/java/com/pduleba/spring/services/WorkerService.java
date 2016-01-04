package com.pduleba.spring.services;

import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;

public interface WorkerService {

	void showQuestions(Collection<QuestionModel> allQuestions);

	void showUsers(Collection<UserModel> allUsers);

	Pair<Collection<QuestionModel>, Collection<UserModel>> getQuestionsAndUsers();

}
