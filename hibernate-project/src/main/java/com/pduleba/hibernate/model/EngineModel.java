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

	@Column(name = "ENGINE_TYPE")
	private String type;
	
	@Column(name = "ENGINE_SIZE")
	private Integer size;

}
