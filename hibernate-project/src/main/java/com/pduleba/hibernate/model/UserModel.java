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

@Entity
@Table(name = "T_USERS")
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

	@OneToMany(mappedBy="question", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // field of @IdClass 
	private Collection<AnswerModel> answers = new LinkedHashSet<>();

	public void addQuestion(QuestionModel question, boolean accepted) {
		AnswerModel answer = new AnswerModel();
		
		answer.setQuestion(question);
		answer.setUser(this);
		answer.setAccepted(accepted);
		
		this.answers.add(answer);
		question.getAnswers().add(answer);
	}
}
