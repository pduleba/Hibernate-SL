package com.pduleba.hibernate.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name="T_USER_DETAILS")
@SecondaryTable(name="T_USER2USER_DETAILS", 
	pkJoinColumns=@PrimaryKeyJoinColumn(name="ID_USER_DETAILS", referencedColumnName="id"),
	uniqueConstraints = @UniqueConstraint(columnNames = "ID_USER")
)
public @Data class UserDetailsModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator="users-details-sequence-generator", strategy = SEQUENCE)
	@SequenceGenerator(name="users-details-sequence-generator", allocationSize = 1, sequenceName = "USERS_SEQ")
	private Long id;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "change_date")
	private Timestamp changeDate;
	
	@Column(name = "details")
	private String details;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(table="T_USER2USER_DETAILS", name="ID_USER", referencedColumnName="id")
	private UserModel assignedTo;
	
}
