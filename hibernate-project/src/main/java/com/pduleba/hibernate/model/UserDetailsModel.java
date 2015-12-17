package com.pduleba.hibernate.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Table(name="T_USER_DETAILS")
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator="roles-sequence-generator")
	@GenericGenerator(name="roles-sequence-generator", strategy="foreign", parameters=
		@Parameter(name = "property", value = "assignedTo")
	)
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "change_date")
	private Timestamp changeDate;
	
	@Column(name = "details")
	private String details;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id")
	private UserModel assignedTo;
	
}
