package com.pduleba.spring.services;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Clob;
import java.text.MessageFormat;
import java.util.Objects;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.pduleba.configuration.ApplicationPropertiesConfiguration;
import com.pduleba.hibernate.model.CarModel;

@Component
class UtilityServiceImpl implements UtilityService, ApplicationPropertiesConfiguration {

	public static final Logger LOG = LoggerFactory.getLogger(UtilityServiceImpl.class);
	
	@Autowired
	private Environment env;
	
	@Override
	public void showCar(Object entity, Mode mode) {
		if (Objects.isNull(entity)) {
			LOG.info("{} :: NOT FOUND", mode);
		} else if (BooleanUtils.isFalse(Hibernate.isInitialized(entity))) {
			LOG.info("{} :: NOT INITIALIZED", entity);
		} else {
			LOG.info("{} :: {}", mode, entity);
		}
	}
	
	@Override
	public CarModel getCar() {
		Clob xml = getClob(env.getProperty(KEY_SPEC_FILE_CLASSPATH_LOCATION));
		Blob image = getBlob(env.getProperty(KEY_IMAGE_FILE_CLASSPATH_LOCATION));
		
		return new CarModel("Audi", 4, xml, image);
	}

	private Blob getBlob(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		Blob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new SerialBlob(StreamUtils.copyToByteArray(inputStream));
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
	
	private Clob getClob(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		Clob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new SerialClob(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")).toCharArray());
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
}
