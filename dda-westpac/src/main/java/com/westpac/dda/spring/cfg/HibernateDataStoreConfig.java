package com.westpac.dda.spring.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@PropertySources({
		@PropertySource(value = { "classpath:/jdbc.properties" }, ignoreResourceNotFound = true),
		@PropertySource(value = { "file:${DDA_CONFIG_DIR}/jdbc.properties" }, ignoreResourceNotFound = true) })
@Configuration
public class HibernateDataStoreConfig {
	@Autowired
	Environment env;

	@Value("${jdbc.driverClassName}")
	public String jdbcDriverClassName;

	@Value("${jdbc.url}")
	public String jdbcUrl;

	@Value("${jdbc.username}")
	public String jdbcUserName;

	@Value("${jdbc.password}")
	public String jdbcPassword;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		final PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return propertySourcesPlaceholderConfigurer;
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUserName);
		dataSource.setPassword(jdbcPassword);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.westpac.dda.pojo" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		// properties.put("hibernate.default_schema", env.getProperty("activiti.database.schema"));
		return properties;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
