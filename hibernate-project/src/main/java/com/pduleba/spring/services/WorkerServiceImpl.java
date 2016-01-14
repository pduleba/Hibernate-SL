package com.pduleba.spring.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
//		LobCreator lobCreator = Hibernate.getLobCreator(session);
//		Blob image = lobCreator.createBlob(stream, length)
		byte[] image = getBytes(env.getProperty(KEY_IMAGE_FILE_CLASSPATH_LOCATION));
		String xml = getString(env.getProperty(KEY_XML_FILE_CLASSPATH_LOCATION));
		
		return new CarModel("Audi", getDateId(), image, xml);
	}

	private byte[] getBytes(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		byte[] resource;
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = StreamUtils.copyToByteArray(inputStream);
					LOG.warn("Resource loaded from {}", classpathLocation);
				}
			} catch (IOException e) {
				LOG.error(MessageFormat.format("Unable to load resource from from {0}", classpathLocation), e);
				resource = new byte[]{};
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
			resource = new byte[]{};
		}
		return resource;
	}
	
	private String getString(String classpathLocation) {
		ClassPathResource imageFile = new ClassPathResource(classpathLocation);
		
		String resource = "";
		if (imageFile.exists()) {
			
			try {
				try (InputStream inputStream = imageFile.getInputStream()) {
					resource = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
					LOG.warn("Resource loaded from {}", classpathLocation);
				}
			} catch (IOException e) {
				LOG.error(MessageFormat.format("Unable to load resource from from {0}", classpathLocation), e);
			}
		} else {
			LOG.warn("Resource do not exist on {}", classpathLocation);
		}
		return resource;
	}
}
