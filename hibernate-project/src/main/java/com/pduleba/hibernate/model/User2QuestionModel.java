package com.pduleba.hibernate.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name = "T_USER2QUESTION_ANSWER")
@AssociationOverrides({
    @AssociationOverride(name = "pk.user",
        joinColumns = @JoinColumn(name = "ID_USER")),
    @AssociationOverride(name = "pk.question",
        joinColumns = @JoinColumn(name = "ID_QUESTION")) })
public @Data class User2QuestionModel {

	@EmbeddedId
	private User2QuestionID pk = new User2QuestionID();

	@Column(name = "ACCEPTED")
	private Boolean accepted;

	@Transient
	public UserModel getUser() {
		return pk.getUser();
	}

	public void setUser(UserModel user) {
		pk.setUser(user);
	}

	@Transient
	public QuestionModel getQuestion() {
		return pk.getQuestion();
	}

	public void setQuestion(QuestionModel question) {
		pk.setQuestion(question);
	}
}
