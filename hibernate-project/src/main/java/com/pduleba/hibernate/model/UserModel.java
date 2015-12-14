package com.pduleba.hibernate.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

public @Data class UserModel extends AbstractBaseModel {

	private String name;
	
	private String surname;
	
	private String login;
	
	private String password;

	private Date birthDate;
	
	private List<OrderModel> orders;
	
	private RoleModel role;
}
