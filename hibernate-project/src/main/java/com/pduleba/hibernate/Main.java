package com.pduleba.hibernate;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.hibernate.model.QuestionModel;
import com.pduleba.hibernate.model.UserModel;
import com.pduleba.spring.controller.SpringController;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private SpringController controller;
	private Worker worker = new Worker();

	public static void main(String[] args) {
		new Main().run();
	}

	public Main() {
	}

	private void run() {
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			this.controller = ctx.getBean(SpringController.class);
			
			LOG.info("Starting...");
	
			LOG.info("######## PRODUCT CRUDS ######## ");
			executeQuestionsCRUD();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOG.error("Thread.sleep() problem :: {}", e.getMessage(), e);
			}
			LOG.info("######## ORDER CRUDS ######## ");
			executeUsersCRUD();
		}
	}

	private void executeQuestionsCRUD() {
		saveQuestions();
		LOG.info(" ----- CREATE complete ----- ");
		List<QuestionModel> allQuestions = getAllQuestions();
		LOG.info(" ----- READ complete ----- ");
		removeAllQuestions(allQuestions);
		LOG.info(" ----- DELETE complete ----- ");
		getAllQuestions();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void executeUsersCRUD() {
		saveUsers();
		LOG.info(" ----- CREATE complete ----- ");
		List<UserModel> allUsers = getAllUsers();
		LOG.info(" ----- READ complete ----- ");
		removeAllUsers(allUsers);
		LOG.info(" ----- DELETE complete ----- ");
		getAllUsers();
		LOG.info(" ----- READ complete ----- ");

		LOG.info("Complete");
	}

	private void removeAllQuestions(List<QuestionModel> questions) {
		this.controller.removeQuestions(questions);
	}

	private void removeAllUsers(List<UserModel> users) {
		this.controller.removeUsers(users);
	}

	private List<QuestionModel> getAllQuestions() {
		List<QuestionModel> allQuestions = this.controller.getAllQuestions();
		worker.showQuestions(allQuestions);

		return allQuestions;
	}

	private List<UserModel> getAllUsers() {
		List<UserModel> allUsers = this.controller.getAllUsers();
		worker.showUsers(allUsers);

		return allUsers;
	}

	private void saveQuestions() {
		Collection<QuestionModel> questions = worker.getQuestionsAndUsers().getLeft();
		this.controller.saveQuestions(questions);
	}

	private void saveUsers() {
		Collection<UserModel> users = worker.getQuestionsAndUsers().getRight();
		this.controller.saveUsers(users);
	}

}
