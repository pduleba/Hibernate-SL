package com.pduleba.converters;

import static java.util.Objects.nonNull;

import javax.persistence.AttributeConverter;

public class BooleanConverter implements AttributeConverter<Boolean, String> {

	private final String VALUE_TRUE = "TT";
	private final String VALUE_FALSE = "FF";
	
	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		return nonNull(attribute) && attribute.booleanValue() ? VALUE_TRUE : VALUE_FALSE;
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		return nonNull(dbData) && VALUE_TRUE.equalsIgnoreCase(dbData) ? Boolean.TRUE : Boolean.FALSE;
	}

}
