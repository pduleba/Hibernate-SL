package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="T_USER2USER_DETAILS", 
		joinColumns=@JoinColumn(name="id_user_details", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="id_user", referencedColumnName="id"),
		uniqueConstraints = @UniqueConstraint(columnNames = "id_user_details")
	)
	private UserDetailsModel userDetails;

}
