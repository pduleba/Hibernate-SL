package com.pduleba.hibernate;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.pduleba.configuration.SpringConfiguration;
import com.pduleba.spring.controller.DBController;
import com.pduleba.spring.controller.QueryController;

public class Main {

	public static final Logger LOG = LoggerFactory.getLogger(Main.class);

	private DBController dbController;
	
	private QueryController queryController;

	public static void main(String[] args) {
		new Main().execute();
	}

	private void configureLogger() {
		final String LOG4J_CLASSPATH_LOCATION = "/config/log4j.properties";
		ClassPathResource log4jResource = new ClassPathResource(LOG4J_CLASSPATH_LOCATION);
		try {
			PropertyConfigurator.configure(log4jResource.getURL());
		} catch (IOException e) {
			throw new InvalidParameterException(MessageFormat.format("Argument ''{0}'' not found!", LOG4J_CLASSPATH_LOCATION));
		}
	}
	
	private void execute() {
		configureLogger();
		
		try (ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class)) {
			dbController = ctx.getBean(DBController.class);
			queryController = ctx.getBean(QueryController.class);

			dbController.createDB();
			
			LOG.info("##### Starting... #####");
			queryController.executeQueries();
			LOG.info("##### Starting... Complete #####");
		}
	}
}
