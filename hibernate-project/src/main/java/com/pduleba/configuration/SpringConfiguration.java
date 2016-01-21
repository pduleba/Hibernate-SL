package com.pduleba.configuration;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pduleba.hibernate.model.CarModel;
import com.pduleba.spring.ApplicationInitializationPackageMarker;

@Configuration
@ComponentScan(basePackageClasses=ApplicationInitializationPackageMarker.class)
@PropertySource("classpath:/config/application.properties")
@EnableTransactionManagement
public class SpringConfiguration implements ApplicationPropertiesConfiguration {
	
	@Autowired
	private Environment env;

	@Bean	// Trick (see doc of @PropertySource for more)
	public static PropertySourcesPlaceholderConfigurer properties(Environment environment) {
	    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
	    propertySourcesPlaceholderConfigurer.setEnvironment(environment);
	    return propertySourcesPlaceholderConfigurer;
	}
	
	@Bean DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(env.getProperty(KEY_DATASOURCE_DRIVER_CLASS));
		dataSource.setUrl(env.getProperty(KEY_DATASOURCE_URL));
		dataSource.setUsername(env.getProperty(KEY_DATASOURCE_USERNAME));
		dataSource.setPassword(env.getProperty(KEY_DATASOURCE_PASSWORD));
		
		return dataSource;
	}
	
	// EntityManager by dataSource
	@Bean LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) throws IOException {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.current_session_context_class", SpringSessionContext.class.getName()); 

        entityManagerFactory.setJpaPropertyMap(properties);
		entityManagerFactory.setPackagesToScan(CarModel.class.getPackage().getName());
		
		HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(va);
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        
		return entityManagerFactory;
	}

	// SessionFactory by EntityManagerFactory
	@Bean public FactoryBean<SessionFactory> getSessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		factory.setEntityManagerFactory(emf);

		return factory;
	}

	// TransactionManager by EntityManagerFactory 
	@Bean JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf, DataSource dataSource) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		jpaTransactionManager.setDataSource(dataSource);
		
		return jpaTransactionManager;
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
