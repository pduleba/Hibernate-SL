package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USER_DETAILS")
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "users-details-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "users-details-sequence-generator", sequenceName = "USERS_DETAILS_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "details")
	private String details;
	
}
