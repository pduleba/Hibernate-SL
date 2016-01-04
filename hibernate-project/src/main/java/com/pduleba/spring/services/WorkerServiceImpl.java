package com.pduleba.spring.services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pduleba.hibernate.model.AnswerModel;
import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.dao.QuestionDao;
import com.pduleba.spring.dao.UserDao;

@Component
class WorkerServiceImpl implements WorkerService {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private QuestionDao questionDao;
	
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.pduleba.spring.services.WorkerService#showQuestions(java.util.Collection)
	 */
	@Override
	public void showQuestions(Collection<QuestionModel> questions) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(questions))) {
			LOG.info("Questions -> NOT INITIALIZED");
		} else if (Objects.isNull(questions) || questions.isEmpty()) {
			LOG.info("Questions -> NOT FOUND");
		} else {
			int index = 0;
			for (QuestionModel p : questions) {
				LOG.info("#> question {} ", ++index);
				displayQuestion(p);
				showAnswers(p.getAnswers());
				LOG.info("-----");
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.pduleba.spring.services.WorkerService#showUsers(java.util.Collection)
	 */
	@Override
	public void showUsers(Collection<UserModel> users) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(users))) {
			LOG.info("Users -> NOT INITIALIZED");
		} else if (Objects.isNull(users) || users.isEmpty()) {
			LOG.info("Users -> NOT FOUND");
		} else {
			int index = 0;
			for (UserModel o : users) {
				LOG.info("#> user {} ", ++index);
				displayUser(o);
				showAnswers(o.getAnswers());
				LOG.info("-----");
			}
		}
	}

	private void showAnswers(Collection<AnswerModel> answers) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(answers))) {
			LOG.info("Answers -> NOT INITIALIZED");
		} else if (Objects.isNull(answers) || answers.isEmpty()) {
			LOG.info("Answers -> NOT FOUND");
		} else {
			int index = 0;
			for (AnswerModel o : answers) {
				LOG.info("#>> answer {} :: accepted {} ", ++index, o.getAccepted());
				displayUser(o.getUser());
				displayQuestion(o.getQuestion());
				LOG.info("-----");
			}
		}		
	}
	
	private void displayQuestion(QuestionModel q) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(q))) {
			LOG.info("QUESTION_MODEL :: NOT INITIALIZED");
		} else if (Objects.nonNull(q)) {
			LOG.info("QUESTION_MODEL :: id = {}, query = {}, dateId = {}", q.getId(), q.getQuery(), q.getDateId());
		} else {
			LOG.info("QUESTION_MODEL :: NOT FOUND");
		}
	}

	private void displayUser(UserModel u) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(u))) {
			LOG.info("USER_MODEL :: NOT INITIALIZED");
		} else if (Objects.nonNull(u)) {
			LOG.info("USER_MODEL :: id = {}, name = {}, sourname = {}, dateId = {}", u.getId(), u.getName(),
					u.getSurname(), u.getDateId());
		} else {
			LOG.info("USER_MODEL :: NOT FOUND");
		}
	}

	private QuestionModel getQuestion(String query, String dateId) {
		QuestionModel question = new QuestionModel();
		question.setQuery(query);
		question.setDateId(dateId);
		
		return question;
	}

	private UserModel getUser(String name, String surname, String dateId) {
		UserModel user = new UserModel();
		user.setName(name);
		user.setSurname(surname);
		user.setDateId(dateId);
		
		return user;
	}
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	/* (non-Javadoc)
	 * @see com.pduleba.spring.services.WorkerService#getQuestionsAndUsers()
	 */
	@Override
	public Pair<Collection<QuestionModel>, Collection<UserModel>> getQuestionsAndUsers() {
		String dateId = getDateId();
		
		QuestionModel what = getQuestion("What?", dateId);
		QuestionModel where = getQuestion("Where?", dateId);
		QuestionModel when = getQuestion("When?", dateId);

		UserModel u1 = getUser("what", "where", dateId);
		UserModel u2 = getUser("what", "when", dateId);
		UserModel u3 = getUser("where", "when", dateId);
		
		Collection<QuestionModel> questions = Arrays.asList(where, what, when);
		Collection<UserModel> users = Arrays.asList(u1, u2, u3);

		// -----------------------------------------------------------------------
		// WARNING : entities of @ManyToMany have to be saved for @IdClass !!! 
		// -----------------------------------------------------------------------
		userDao.saveAll(users);
		questionDao.saveAll(questions);

		u1.addQuestion(what, true);
		u1.addQuestion(where, true);
		
		u2.addQuestion(what, false);
		u2.addQuestion(when, true);

		u3.addQuestion(where, false);
		u3.addQuestion(when, false);
		
		return Pair.of(questions, users);
	}
}
