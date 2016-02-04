package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "T_USER_DETAILS")
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "details")
	private String details;

}
