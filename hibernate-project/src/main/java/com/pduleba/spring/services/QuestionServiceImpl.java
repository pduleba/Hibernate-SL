package com.pduleba.spring.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.spring.dao.QuestionDao;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDao questionDao;

	@Override
	public void saveAll(Collection<QuestionModel> questions) {
		questionDao.saveAll(questions);
	}

	@Override
	public List<QuestionModel> getAllQuestions() {
		return questionDao.getAllQuestions();
	}

	@Override
	public void removeAll(List<QuestionModel> questions) {
		questionDao.removeAll(questions);
	}
}
