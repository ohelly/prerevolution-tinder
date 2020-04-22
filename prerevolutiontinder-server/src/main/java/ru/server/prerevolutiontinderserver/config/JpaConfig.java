package ru.server.prerevolutiontinderserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setPackagesToScan("ru.server.prerevolutiontinderserver");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setJpaProperties(hibernateProperties());
		return emf;
	}

	@Bean
	public Properties hibernateProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.hibernate.hbm2ddl.auto", "validate");
		prop.setProperty("hibernate.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		prop.setProperty("hibernate.show_sql", "true");
		return prop;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource("jdbc:hsqldb:file:C:\\Users\\vladik\\Desktop\\hsqldb-2.5.0\\hsqldb\\bin/tinderdb");
		driverManagerDataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
		return driverManagerDataSource;
	}
}
