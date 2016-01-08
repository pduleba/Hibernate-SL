package com.pduleba.hibernate.model;

import java.io.Serializable;

import lombok.Data;

public @Data class CarPK implements Serializable {

	private static final long serialVersionUID = -8942935027275183098L;

	private Long id;
	
	private String name;
}
