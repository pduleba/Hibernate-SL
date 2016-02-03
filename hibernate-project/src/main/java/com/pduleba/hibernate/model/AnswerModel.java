package com.pduleba.hibernate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "T_USER2QUESTION_ANSWER")
@IdClass(value = AnswerModelId.class)
@EqualsAndHashCode(exclude = {"user","question"})
public @Data class AnswerModel implements Serializable {

	private static final long serialVersionUID = 9109371155760306697L;

	@Id
//	@Column(name = "ID_QUESTION")
	private Long questionId;

	@Id
//	@Column(name = "ID_USER")
	private Long userId;

	@Column(name = "ACCEPTED")
	private Boolean accepted;

	@ManyToOne
//	@JoinColumn(name = "userId", updatable = false, insertable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_USERS"))
	@PrimaryKeyJoinColumn(name = "ID_USER", referencedColumnName = "id")
	/*
	 * if this JPA model doesn't create a table for the "T_USER2QUESTION_ANSWER" entity,
	 * please comment out the @PrimaryKeyJoinColumn, and use the ff:
	 * 
	 * @JoinColumn(name = "userId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "userId", updatable = false, insertable = false,
	 * referencedColumnName = "id")
	 */
	private UserModel user;

	@ManyToOne
//	@JoinColumn(name = "questionId", updatable = false, insertable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_U2Q_QUESTIONS"))
	@PrimaryKeyJoinColumn(name = "ID_QUESTION", referencedColumnName = "id")
	/*
	 * the same goes here: if this JPA model doesn't create a table for the
	 * "T_USER2QUESTION_ANSWER" entity, please comment out the @PrimaryKeyJoinColumn, and use
	 * the ff:
	 * 
	 * @JoinColumn(name = "questionId", updatable = false, insertable = false)
	 * or @JoinColumn(name = "questionId", updatable = false, insertable =
	 * false, referencedColumnName = "id")
	 */
	private QuestionModel question;
}
