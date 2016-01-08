package com.pduleba.hibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_SPORT_CAR")
@AttributeOverride(name = "baseName", column = @Column(name = "SPORT_NAME"))				// This override column name of SuperClass
@NoArgsConstructor
@ToString(callSuper = true)
public class SportCarModel extends AbstractCarModel {

	public SportCarModel(String name, String dateId) {
		super(name, dateId);
	}

}
