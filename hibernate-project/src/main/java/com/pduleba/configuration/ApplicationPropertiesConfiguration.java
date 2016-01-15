package com.pduleba.configuration;

public interface ApplicationPropertiesConfiguration {

	// DataSource properties
	String KEY_DATASOURCE_DRIVER_CLASS = "jdbc.driverClassName";
	String KEY_DATASOURCE_URL = "jdbc.url";
	String KEY_DATASOURCE_USERNAME = "jdbc.username";
	String KEY_DATASOURCE_PASSWORD = "jdbc.password";

	// Hibernate properties
	String KEY_HIBERNATE_PROPERTIES_LOCATION = "hibernate.properties.location";

	// Application properties
	String KEY_XML_FILE_CLASSPATH_LOCATION = "xml.file.classpath.location";

}
