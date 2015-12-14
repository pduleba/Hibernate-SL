package com.pduleba.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.pduleba.spring.ApplicationInitializationPackageMarker;

@Configuration
@ComponentScan(basePackageClasses=ApplicationInitializationPackageMarker.class)
@PropertySource("classpath:/config/application.properties")
public class SpringConfiguration {

	@Autowired
	private Environment env;
	
}
