package com.pduleba.hibernate.model;

import java.util.List;

import lombok.Data;

public @Data class RoleModel extends AbstractBaseModel {

	private String name;

	private List<UserModel> assignedTo;
	
}
