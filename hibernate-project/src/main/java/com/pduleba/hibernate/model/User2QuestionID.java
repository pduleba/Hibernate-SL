package com.pduleba.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
public @Data class User2QuestionID implements Serializable {

	private static final long serialVersionUID = 8429147668468461697L;

	private Long userId;

	private Long questionId;
	
}
