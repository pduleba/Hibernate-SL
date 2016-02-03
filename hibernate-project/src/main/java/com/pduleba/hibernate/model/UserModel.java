package com.pduleba.hibernate.model;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_USERS")
@EqualsAndHashCode(exclude = "questions")
public @Data class UserModel {

	@Id
	@Column(name = "ID")
	@GeneratedValue(generator = "users-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "users-sequence-generator", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="DATE_ID")
	private String dateId;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<User2QuestionModel> questions = new LinkedHashSet<>();

	public void addQuestion(QuestionModel question, boolean accepted) {
		User2QuestionModel u2q = new User2QuestionModel();
		
		u2q.setUser(this);
		u2q.setQuestion(question);
		
		question.getUsers().add(u2q);
		questions.add(u2q);
	}

}
