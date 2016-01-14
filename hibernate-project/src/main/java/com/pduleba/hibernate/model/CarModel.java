package com.pduleba.hibernate.model;

import static java.util.Objects.nonNull;

import java.sql.Blob;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_CAR")
@NoArgsConstructor
//@ToString(exclude = "image")
public @Data class CarModel {

	public CarModel(String name, String dateId, Blob image, Clob documentation) {
		super();
		this.name = name;
		this.dateId = dateId;
		this.image = image;
		this.documentation = documentation;
	}

	@Id
	@GeneratedValue(generator = "car-sequence-generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "car-sequence-generator", sequenceName = "CAR_SEQ", allocationSize = 1, initialValue = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DATE_ID")
	private String dateId;

	@Lob
	@Column(name = "IMAGE")
	private Blob image;
	
	@Lob
	@Column(name = "documentation")
	private Clob documentation;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CarModel [id=");
		sb.append(id);
		sb.append(", name=");
		sb.append(name);
		sb.append(", dateId=");
		sb.append(dateId);
		sb.append(", image=");
		sb.append(nonNull(image));
		sb.append(", documentation=");
		sb.append(nonNull(documentation));
		sb.append("]");
		return sb.toString();
	}
}
