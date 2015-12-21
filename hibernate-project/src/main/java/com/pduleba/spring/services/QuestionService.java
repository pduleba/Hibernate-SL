package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.QuestionModel;

public interface QuestionService {

	void saveAll(Collection<QuestionModel> questions);

	List<QuestionModel> getAllQuestions();

	void removeAll(List<QuestionModel> questions);

}
