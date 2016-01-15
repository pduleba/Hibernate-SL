package com.pduleba.hibernate.model;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "T_ORDERS",
			joinColumns = @JoinColumn(name="id_user"))
	private Collection<OrderModel> orders = new LinkedHashSet<>();

}
