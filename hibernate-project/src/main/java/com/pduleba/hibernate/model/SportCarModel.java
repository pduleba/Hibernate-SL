package com.pduleba.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = "SportCarModel")					// <- Discriminator Value
@ToString(callSuper = true)
@NoArgsConstructor
public class SportCarModel extends AbstractCarModel {

	public SportCarModel(String name, String dateId) {
		super(name, dateId);
	}

}
