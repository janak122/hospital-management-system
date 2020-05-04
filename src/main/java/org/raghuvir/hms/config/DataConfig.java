package org.raghuvir.hms.config;

import org.hibernate.ejb.HibernatePersistence;

import java.util.Properties;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.raghuvir.hms.repositories")
@PropertySource("classpath:database.properties")
public class DataConfig {
	private final String PROPERTY_DRIVER = "driver";
	private final String PROPERTY_URL = "url";
	private final String PROPERTY_USERNAME = "username";
	private final String PROPERTY_PASSWORD = "password";
	private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";
	
	@Autowired
	Environment environment;

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(dataSource());
		lfb.setPersistenceProviderClass(HibernatePersistence.class);
		lfb.setPackagesToScan("org.raghuvir.hms.beans");
		lfb.setJpaProperties(hibernateProps());
		return lfb;
	}

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
//		ds.setUrl(environment.getProperty(PROPERTY_URL));
//		ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
//		ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
//		ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("ram");
		ds.setPassword("ram");
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		return ds;
	}

	Properties hibernateProps() {
		Properties properties = new Properties();
//		properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
//		properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
		properties.setProperty(PROPERTY_DIALECT, "org.hibernate.dialect.OracleDialect");
		properties.setProperty(PROPERTY_SHOW_SQL, "true");
		
		return properties;
	}

	@Bean
	JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
