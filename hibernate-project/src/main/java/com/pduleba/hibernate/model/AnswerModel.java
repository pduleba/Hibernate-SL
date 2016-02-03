package com.pduleba.hibernate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_USER2QUESTION_ANSWER")
@IdClass(value = AnswerModelId.class)
@EqualsAndHashCode(exclude = {"user","question"})
public @Data class AnswerModel implements Serializable {

	private static final long serialVersionUID = 9109371155760306697L;

	@Column(name = "ACCEPTED")
	private Boolean accepted;

	@Id
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private UserModel user;

	@Id
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private QuestionModel question;
}
