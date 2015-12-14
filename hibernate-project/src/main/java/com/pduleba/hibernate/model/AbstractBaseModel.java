package com.pduleba.hibernate.model;

import java.sql.Date;

import lombok.Data;

public @Data abstract class AbstractBaseModel {

	protected Long id;

	protected Date createDate;
	
	protected Date changeDate;
}
