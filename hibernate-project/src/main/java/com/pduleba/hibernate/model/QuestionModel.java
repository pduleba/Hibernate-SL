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
@Table(name="T_QUESTIONS")
@EqualsAndHashCode(exclude = "users")
public @Data class QuestionModel {

	@Id
	@Column(name="ID")
	@GeneratedValue(generator="question-sequence-generator", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="question-sequence-generator", sequenceName="QUESTIONS_SEQ", initialValue=1, allocationSize=1)
	private Long id;
	
	@Column(name = "QUERY")
	private String query;
	
	@Column(name = "DATE_ID")
	private String dateId;

	@OneToMany(mappedBy="pk.question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<User2QuestionModel> users = new LinkedHashSet<>();
}
