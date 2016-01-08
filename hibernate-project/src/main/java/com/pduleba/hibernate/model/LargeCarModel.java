package com.pduleba.hibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "T_LARGE_CAR")
@AttributeOverride(name = "baseName", column = @Column(name = "LARGE_NAME"))				// This override column name of SuperClass
@NoArgsConstructor
@ToString(callSuper = true)
public class LargeCarModel extends AbstractCarModel {
	
	public LargeCarModel(String name, Long part_size, Long part_weight, String dateId) {
		super(name, dateId);
		this.part_size = part_size;
		this.part_weight = part_weight;
	}

	@Column(name = "PART_SIZE")
	private Long part_size;
	
	@Column(name = "PART_WEIGHT")
	private Long part_weight;

}
