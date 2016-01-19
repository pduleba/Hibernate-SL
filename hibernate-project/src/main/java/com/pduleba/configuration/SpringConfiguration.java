package com.pduleba.configuration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.ApplicationInitializationPackageMarker;

@Configuration
@ComponentScan(basePackageClasses=ApplicationInitializationPackageMarker.class)
@PropertySource("classpath:/config/application.properties")
@EnableTransactionManagement
public class SpringConfiguration implements ApplicationPropertiesConfiguration {

	public static final String TRANSACTION_MANAGER_JPA = "transactionManagerJpa";
	public static final String TRANSACTION_MANAGER_HIBERNATE = "transactionManagerHibernate";

	public static final String DAO_JPA = "daoJpa";
	public static final String DAO_HIBERNATE = "daoHibernate";
	
	@Autowired
	private Environment env;

	@Bean	// Trick (see doc of @PropertySource for more)
	public static PropertySourcesPlaceholderConfigurer properties(Environment environment) {
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    propertySourcesPlaceholderConfigurer.setEnvironment(environment);
	    return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(env.getProperty(KEY_DATASOURCE_DRIVER_CLASS));
		dataSource.setUrl(env.getProperty(KEY_DATASOURCE_URL));
		dataSource.setUsername(env.getProperty(KEY_DATASOURCE_USERNAME));
		dataSource.setPassword(env.getProperty(KEY_DATASOURCE_PASSWORD));
		
		return dataSource;
	}
	
	// ---------------------------------------
	// 	   Session Factory + Transactions
	// ---------------------------------------
	@Bean
	@Autowired LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws IOException {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(CarModel.class.getPackage().getName());
		sessionFactory.setHibernateProperties(getHibernateProperties());

		return sessionFactory;
	}

	@Bean(name = TRANSACTION_MANAGER_HIBERNATE)
	@Autowired PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
	// ---------------------------------------
	// 	Entity Manager Factory + Transactions
	// ---------------------------------------
	@Bean
	@Autowired 
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws IOException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		
		em.setDataSource(dataSource);
		em.setPackagesToScan(CarModel.class.getPackage().getName());
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getHibernateProperties());	 

		return em;
	}
	
	@Bean(name = TRANSACTION_MANAGER_JPA)
	@Autowired PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
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
