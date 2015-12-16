package com.pduleba.hibernate.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "T_USERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "login")
})
@SequenceGenerator(name="users-sequence-generator", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 0)
public @Data class UserModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator="users-sequence-generator", strategy=GenerationType.SEQUENCE)
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

	@OneToOne(fetch=EAGER, cascade=ALL, optional=false, orphanRemoval=true)
	@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")
	private UserDetailsModel userDetails;
	
//	private List<OrderModel> orders;
}
