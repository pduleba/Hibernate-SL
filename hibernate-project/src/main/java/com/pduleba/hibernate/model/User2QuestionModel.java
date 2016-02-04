package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USER2QUESTION_ANSWER")
public @Data class User2QuestionModel {

	@EmbeddedId
	private User2QuestionID id = new User2QuestionID();

	@Column(name = "ACCEPTED")
	private Boolean accepted;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private UserModel user;

	@ManyToOne
	@MapsId("questionId")
	@JoinColumn(name = "questionId", referencedColumnName = "id")
	private QuestionModel question;

}
