package com.pduleba.hibernate.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "T_USERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "login")
})
public @Data class UserModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator="users-sequence-generator", strategy = SEQUENCE)
	@SequenceGenerator(name="users-sequence-generator", allocationSize = 1, sequenceName = "USERS_SEQ")
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;
	
	@Column(name = "change_date")
	private Timestamp changeDate;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;

	@Column(name = "birth_date")
	private Timestamp birthDate;

	@OneToOne(fetch=EAGER, mappedBy="assignedTo", cascade=CascadeType.ALL)
	private UserDetailsModel userDetails;
	
//	private List<OrderModel> orders;
}
