package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USERS")
public @Data class UserModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "users-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "users-sequence-generator", sequenceName = "USERS_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private UserDetailsModel userDetails;

}
