package com.slk.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;



@Configuration
@EnableJpaRepositories(basePackages={"com.slk.app"})
@EnableTransactionManagement
public class DataBaseConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
		entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("entitymanager.packages.to.scan"));
		
		entityManagerFactoryBean.setJpaProperties(hibProperties());
		
		return entityManagerFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.id.new_generator_mappings", Boolean.FALSE);
        
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		try {
			dataSource.setDriverClass(environment.getRequiredProperty("jdbc.driverClassName"));

			dataSource.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
			dataSource.setUser(environment.getRequiredProperty("jdbc.username"));
			dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
			dataSource.setDebugUnreturnedConnectionStackTraces(true);
			dataSource.setAcquireIncrement(Integer.parseInt(environment.getProperty("jdbc.c3p0.acquireIncrement")));
			dataSource.setMinPoolSize(Integer.parseInt(environment.getProperty("jdbc.c3p0.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(environment.getProperty("jdbc.c3p0.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(environment.getProperty("jdbc.c3p0.maxIdleTime")));
		} catch (Throwable th) {
			// LOGGER.error("***** FATAL Failed to create DataSource", th);
			th.printStackTrace();
		}

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
}
