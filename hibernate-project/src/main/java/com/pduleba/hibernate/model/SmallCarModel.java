package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = "SmallCarModel")					// <- Discriminator Value
@ToString(callSuper = true)
@NoArgsConstructor
public class SmallCarModel extends AbstractCarModel {

	public SmallCarModel(String name, String partName, String dateId) {
		super(name, dateId);
		this.partName = partName;
	}

	@Column(name = "PART_NAME")
	private String partName;

}
