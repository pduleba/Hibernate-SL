package com.pduleba.hibernate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="T_USER_DETAILS")
@SequenceGenerator(name="users-details-sequence-generator", sequenceName = "USERS_DETAILS_SEQ", allocationSize = 1, initialValue = 0)
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users-details-sequence-generator")
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "change_date")
	private Timestamp changeDate;
	
	@Column(name = "details")
	private String details;
	
}
