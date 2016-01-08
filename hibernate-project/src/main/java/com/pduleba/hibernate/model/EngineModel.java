package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public @Data class EngineModel {

	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "SIZE")
	private Integer size;

}
