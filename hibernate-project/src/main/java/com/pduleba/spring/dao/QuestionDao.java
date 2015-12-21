package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import com.pduleba.hibernate.model.QuestionModel;

public interface QuestionDao {

	List<QuestionModel> getAllQuestions();

	void saveAll(Collection<QuestionModel> questions);

	void removeAll(List<QuestionModel> questions);

}
