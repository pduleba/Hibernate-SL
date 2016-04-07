package com.pduleba.configuration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pduleba.hibernate.model.Rent;
import com.pduleba.spring.ApplicationInitializationPackageMarker;

@Configuration
@ComponentScan(basePackageClasses=ApplicationInitializationPackageMarker.class)
@PropertySource("classpath:/config/application.properties")
@EnableTransactionManagement
public class SpringConfiguration implements ApplicationPropertiesConfiguration {
	
	private static final String DATA_SOURCE_ID = "dataSource";
	@Autowired
	private Environment env;

	@Bean	// Trick (see doc of @PropertySource for more)
	public static PropertySourcesPlaceholderConfigurer properties(Environment environment) {
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    propertySourcesPlaceholderConfigurer.setEnvironment(environment);
	    return propertySourcesPlaceholderConfigurer;
	}

	@Bean(name=DATA_SOURCE_ID)
	public DataSource db() {
	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    builder.setType(EmbeddedDatabaseType.HSQL);
	    builder.addScript("classpath:sql/dbScript.sql");
	    builder.addScript("classpath:sql/dbRecordsInitialization.sql");
	    
	    return builder.build();
	}
	
	@Bean
	@DependsOn(value = DATA_SOURCE_ID)
	public MethodInvokingFactoryBean databaseManagerSwing() {
	    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
	    methodInvokingFactoryBean.setTargetClass(DatabaseManagerSwing.class);
	    methodInvokingFactoryBean.setTargetMethod("main");
		methodInvokingFactoryBean.setArguments(
				new Object[] { 
						"--url", 
						env.getProperty(KEY_DATASOURCE_URL), 
						"--user", 
						env.getProperty(KEY_DATASOURCE_USERNAME), 
						"--password", 
						env.getProperty(KEY_DATASOURCE_PASSWORD) });
	    
	    return methodInvokingFactoryBean; 
	}
	
	@Bean 
	@Autowired LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(Rent.class.getPackage().getName());
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}

	@Bean 
	@Autowired PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	private Properties getHibernateProperties() throws IOException {
		Properties prop = new Properties();
		Resource resource = new ClassPathResource(env.getProperty(KEY_HIBERNATE_PROPERTIES_LOCATION));
		
		if (resource.isReadable()) {
			prop.load(resource.getInputStream());
		} else {
			throw new IllegalStateException(MessageFormat.format("{0} not readable", resource.getFilename()));
		}
		
		return prop;
	}
}
