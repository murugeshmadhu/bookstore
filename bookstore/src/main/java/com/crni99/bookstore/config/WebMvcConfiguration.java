package com.crni99.bookstore.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@SuppressWarnings("deprecation")
@Configuration
@PropertySource("classpath:application.properties")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	private Environment env;

	public WebMvcConfiguration(Environment env) {
		this.env = env;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.addBasenames("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public DataSource securityDataSource() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try {
			securityDataSource.setDriverClass(env.getProperty("spring.datasource.driver-class-name"));
		} catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}

		securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		securityDataSource.setUser(env.getProperty("spring.datasource.username"));
		securityDataSource.setPassword(env.getProperty("spring.datasource.password"));

		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	private int getIntProperty(String propName) {

		String propVal = env.getProperty(propName);

		int intPropVal = Integer.parseInt(propVal);

		return intPropVal;
	}

}
