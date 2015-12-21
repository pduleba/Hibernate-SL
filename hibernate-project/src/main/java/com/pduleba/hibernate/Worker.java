package com.pduleba.hibernate;

import java.text.MessageFormat;
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

import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;

class Worker {
	
	public static final Logger LOG = LoggerFactory.getLogger(Worker.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	void showQuestions(Collection<QuestionModel> questions) {
		showQuestions(questions, true);
	}	
	
	void showQuestions(Collection<QuestionModel> questions, boolean showUsers) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(questions))) {
			LOG.info("Questions -> NOT INITIALIZED");
		} else if (Objects.isNull(questions) || questions.isEmpty()) {
			LOG.info("Questions -> NOT FOUND");
		} else {
			int index = 0;
			for (QuestionModel p : questions) {
				LOG.info("#> question {} ", ++index);
				displayQuestion(p);
				if (showUsers) {
					showUsers(p.getUsers(), false);
					LOG.info("-----");
				}
			}
		}
	}
	void showUsers(Collection<UserModel> users) {
		showUsers(users, true);
	}

	void showUsers(Collection<UserModel> users, boolean showQuestions) {
		if (BooleanUtils.isFalse(Hibernate.isInitialized(users))) {
			LOG.info("Users -> NOT INITIALIZED");
		} else if (Objects.isNull(users) || users.isEmpty()) {
			LOG.info("Users -> NOT FOUND");
		} else {
			int index = 0;
			for (UserModel o : users) {
				LOG.info("#> user {} ", ++index);
				displayUser(o);
				if (showQuestions) {
					showQuestions(o.getQuestions(), false);
					LOG.info("-----");
				}
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
	
	public Pair<Collection<QuestionModel>, Collection<UserModel>> getQuestionsAndUsers() {
		String dateId = getDateId();
		
		QuestionModel what = getQuestion("What?", dateId);
		QuestionModel where = getQuestion("Where?", dateId);
		QuestionModel when = getQuestion("When?", dateId);

		UserModel u1 = getUser("what", "where", dateId);
		u1.getQuestions().add(what);
		what.getUsers().add(u1);
		u1.getQuestions().add(where);
		where.getUsers().add(u1);
		
		UserModel u2 = getUser("what", "when", dateId);
		u2.getQuestions().add(what);
		what.getUsers().add(u2);
		u2.getQuestions().add(when);
		when.getUsers().add(u2);

		UserModel u3 = getUser("where", "when", dateId);
		u3.getQuestions().add(where);
		where.getUsers().add(u3);
		u3.getQuestions().add(when);
		when.getUsers().add(u3);
		
		Collection<QuestionModel> questions = Arrays.asList(where, what, when);
		Collection<UserModel> users = Arrays.asList(u1, u2, u3);
		
		return Pair.of(questions, users);
	}

	private String getName(String name, String dateId) {
		return MessageFormat.format("{0}-{1}", name, dateId);
	}
}
