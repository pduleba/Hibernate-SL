package com.pduleba.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="T_USER_DETAILS")
@SequenceGenerator(name="user-details-sequence-generator", sequenceName="USER_DETAILS_SEQ", initialValue=1, allocationSize=1)
@SecondaryTable(name = "t_user2orders", 
	pkJoinColumns={ @PrimaryKeyJoinColumn(name="id_user_details", referencedColumnName="id") },
	uniqueConstraints={ @UniqueConstraint(columnNames="id_user") }
)
@EqualsAndHashCode(exclude="user")
public @Data class UserDetailsModel {

	@Id
	@Column(name="id")
	@GeneratedValue(generator="user-details-sequence-generator", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name="user_details")
	private String userDetails;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(table="t_user2orders", name="id_user", referencedColumnName="id")
	private UserModel user;

}
