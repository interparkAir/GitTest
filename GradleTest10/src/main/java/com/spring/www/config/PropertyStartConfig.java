package com.spring.www.config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;


public class PropertyStartConfig extends Properties{

	private final static String JDBC_PROPERTY = "mybatis/JDBC.xml";
	
	@Value("#{db['db.driver']}")
	private String jdbcDriverClassName;
	
	public  PropertyStartConfig () throws Exception{
		
//	
		System.out.println("PropertyPlaceholderConfigurerPropertyPlaceholderConfigurer");
		
		
		Resource propFile = new ClassPathResource(JDBC_PROPERTY);
			
		this.loadFromXML(propFile.getInputStream());
		System.out.println("PropertiesPropertiesProperties");
		System.out.println("props.getProperty(driver)===>>"+this.getProperty("driver"));
		System.out.println("props.getProperty(url)===>>"+this.getProperty("url"));
				
		System.out.println("jdbcDriverClassName22==>"+this.jdbcDriverClassName);
		
	}
	
}
