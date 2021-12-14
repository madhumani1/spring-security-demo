/**
 * 
 */
package com.madhu.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author 15197
 * Configuration class
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.madhu.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer {
	
	// set up variable to hold the properties
	@Autowired
	private Environment env;
	
	// setup a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver()	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	// define a bean for our security dataSource
	@Bean
	public DataSource securityDataSource()	{
		// create Connection Pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set JDBC driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		// log the connection props
		// just to make sure we are REALLY reading data from properties file
		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		securityDataSource.setInitialPoolSize(
				getIntProperty("connection.pool.initialPoolSize"));

		securityDataSource.setMinPoolSize(
				getIntProperty("connection.pool.minPoolSize"));

		securityDataSource.setMaxPoolSize(
				getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(
				getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// need a helper method 
	// read environment property and convert to int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName.trim());
		// now convert to int
		int intPropVal = Integer.parseInt(propVal.trim());
		return intPropVal;
	}
}
