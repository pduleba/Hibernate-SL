package com.pduleba.hibernate.model;

import java.io.Serializable;

import lombok.Data;

public @Data class AnswerModelId implements Serializable {

	private static final long serialVersionUID = -99297205410693493L;

	private QuestionModel question;
	
	private UserModel user;
}
