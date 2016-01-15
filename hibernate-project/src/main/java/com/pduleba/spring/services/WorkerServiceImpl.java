package com.pduleba.spring.services;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.sql.NClob;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.sql.rowset.serial.SerialClob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.pduleba.configuration.ApplicationPropertiesConfiguration;
import com.pduleba.hibernate.model.CarModel;

import oracle.jdbc.rowset.OracleSerialClob;

@Component
class WorkerServiceImpl implements WorkerService, ApplicationPropertiesConfiguration {
	
	public static final Logger LOG = LoggerFactory.getLogger(WorkerServiceImpl.class);
	private final static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private Environment env;
	
	private String getDateId() {
		return FORMAT.format(LocalTime.now());
	}
	
	@Override
	public CarModel getCar() {
		final Charset UTF8 = Charset.forName("UTF-8");
		String xmlFile = env.getProperty(KEY_XML_FILE_CLASSPATH_LOCATION);
		NClob nclob = getNClob(xmlFile, UTF8);
		Clob clob = getClob(xmlFile, UTF8);
		
		return new CarModel("Audi", getDateId(), clob, nclob);
	}
	
	private Clob getClob(String classpathLocation, Charset charset) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		Clob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new SerialClob(StreamUtils.copyToString(inputStream, charset).toCharArray());
					LOG.warn("Resource loaded from {}", classpathLocation);
				}
			} catch (Exception e) {
				LOG.error(MessageFormat.format("Unable to load resource from from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
	
	private NClob getNClob(String classpathLocation, Charset charset) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		NClob resource = null;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = new OracleSerialClob(StreamUtils.copyToString(inputStream, charset).toCharArray());
					LOG.warn("Resource loaded from {}", classpathLocation);
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
