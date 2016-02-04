package com.pduleba.hibernate.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="T_USER_DETAILS")
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "users-details-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "users-details-sequence-generator", sequenceName = "USERS_DETAILS_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "change_date")
	private Timestamp changeDate;
	
	@Column(name = "details")
	private String details;

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_user")
	private UserModel assignedTo;
	
}
