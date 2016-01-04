package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_USER2QUESTION_ANSWER")
@EqualsAndHashCode(exclude = {"user", "question"})
public @Data class User2QuestionModel {

	@Id
	@GeneratedValue(generator="user2question-sequence-generator", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="user2question-sequence-generator", sequenceName = "USER_2_QUESTION_SEQ", allocationSize=1, initialValue=1)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USER")
	private UserModel user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_QUESTION")
	private QuestionModel question;
	
	@Column(name = "ACCEPTED")
	private Boolean accepted;
}
