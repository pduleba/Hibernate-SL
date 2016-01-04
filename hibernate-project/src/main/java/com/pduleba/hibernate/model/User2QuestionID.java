package com.pduleba.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Embeddable
public @Data class User2QuestionID implements Serializable {

	private static final long serialVersionUID = 8429147668468461697L;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_USERS"))
//	@PrimaryKeyJoinColumn(name = "userId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_USERS"))
	private UserModel user;

	@ManyToOne
	@JoinColumn(name = "questionId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_QUESTIONS"))
//	@PrimaryKeyJoinColumn(name = "questionId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_QUESTIONS"))
	private QuestionModel question;	
	
}
