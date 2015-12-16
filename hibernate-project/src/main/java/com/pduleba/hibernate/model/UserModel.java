package com.pduleba.hibernate.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "T_USERS")
@SequenceGenerator(name = "users-sequence-generator", sequenceName = "USERS_SEQ", initialValue = 0, allocationSize = 1)
public class UserModel {

	private Long id;
	private String name;
	private UserDetailsModel userDetails;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "users-sequence-generator", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
		if (userDetails != null) {
			userDetails.setId(id);
		}
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(fetch = EAGER, cascade = ALL)
	@JoinColumn(name = "id", insertable = false, updatable = false)
	public UserDetailsModel getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetailsModel userDetails) {
		this.userDetails = userDetails;
	}
}
