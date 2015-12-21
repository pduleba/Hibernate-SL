package com.pduleba.spring.dao;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.pduleba.hibernate.model.QuestionModel;

@Repository
@Transactional
public class QuestionDaoImpl extends HibernateDaoSupport implements QuestionDao {

	public static final Logger LOG = LoggerFactory.getLogger(QuestionDaoImpl.class);

	@Autowired
	public QuestionDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	public List<QuestionModel> getAllQuestions() {
		return getHibernateTemplate().loadAll(QuestionModel.class);
	}

	@Override
	public void saveAll(Collection<QuestionModel> questions) {
		for (QuestionModel question : questions) {
			getHibernateTemplate().save(question);
		}
	}

	@Override
	public void removeAll(List<QuestionModel> questions) {
		getHibernateTemplate().deleteAll(questions);
	}
}
