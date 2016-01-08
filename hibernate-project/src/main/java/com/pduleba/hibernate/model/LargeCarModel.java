package com.pduleba.hibernate.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@DiscriminatorValue(value = "LargeCarModel")					// <- Discriminator Value
@ToString(callSuper = true)
@NoArgsConstructor
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
