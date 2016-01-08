package com.pduleba.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_SPORT_CAR")									// <- required for this strategy kind 
@ToString(callSuper = true)
@NoArgsConstructor
public class SportCarModel extends AbstractCarModel {

	public SportCarModel(String name, String dateId) {
		super(name, dateId);
	}

}
