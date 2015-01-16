package com.spring.www.config;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;




@Configuration
@ComponentScan(basePackages = { "com.spring.www" })
//@PropertySource(name="dbProp", value="classpath:/mybatis/JDBC.xml")
//@ImportResource("classpath:mybatis/context-mybatis.xml")
public class ModuleConfig {

	
	
	
	@Value("#{aa['driver']}")
	private String jdbcDriverClassName;
	
	@Value("#{['url']}")
	private String jdbcUrl;
	
	@Value("#{user}")
	private String jdbcUsername;
	
	@Value("#{pwd}")
	private String jdbcPassword;
	
	@Value("#{maxActive}")
	private int jdbcMaxActive;
	
	@Value("#{maxIdle}")
	private int jdbcMaxIdle;
	
	@Value("#{minIdle}")
	private int jdbcMinIdle;
	
	@Value("#{maxWait}")
	private int jdbcMaxWait;
	
	private final static String JDBC_PROPERTY = "mybatis/JDBC.xml";
	
	
	/*
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
		PropertyPlaceholderConfigurer proConfigurer = new PropertyPlaceholderConfigurer();
//		proConfigurer.setLocations(new Resource[]{new ClassPathResource(JDBC_PROPERTY)});
		proConfigurer.setLocation(new ClassPathResource(JDBC_PROPERTY));
		System.out.println("PropertyPlaceholderConfigurerPropertyPlaceholderConfigurer");
		System.out.println("proConfigurer.getOrder()===>>"+proConfigurer.getOrder());
		
		return proConfigurer;
	}
	
	@Bean
	public static Properties dbconnect(@Value("classpath:/mybatis/JDBC.xml") Resource propFile) throws Exception{
		Properties props = new Properties();
		props.loadFromXML(propFile.getInputStream());
		System.out.println("PropertiesPropertiesProperties");
		System.out.println("props.getProperty(driver)===>>"+props.getProperty("driver"));
		System.out.println("props.getProperty(url)===>>"+props.getProperty("url"));
		
		return props;
	}
	*/
	
	@Bean
	public DataSource dataSource() throws Exception{
		
		Properties props = new Properties();
		Resource propFile = new ClassPathResource(JDBC_PROPERTY);
		
		props.loadFromXML(propFile.getInputStream());
		System.out.println("PropertiesPropertiesProperties");
		System.out.println("props.getProperty(driver)===>>"+props.getProperty("driver"));
		System.out.println("props.getProperty(url)===>>"+props.getProperty("url"));
		this.jdbcDriverClassName = props.getProperty("driver");
		this.jdbcUrl=props.getProperty("url");
		this.jdbcUsername=props.getProperty("user");
		this.jdbcPassword=props.getProperty("pwd");
		this.jdbcMaxActive=Integer.parseInt(props.getProperty("maxActive"));
		this.jdbcMaxIdle=Integer.parseInt(props.getProperty("maxIdle"));
		this.jdbcMinIdle=Integer.parseInt(props.getProperty("minIdle"));
		this.jdbcMaxWait=Integer.parseInt(props.getProperty("maxWait"));
		
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(jdbcDriverClassName);
		
		System.out.println("this.jdbcDriverClassName==>>>"+this.jdbcDriverClassName);
		System.out.println("this.jdbcUrl==>>>"+this.jdbcUrl);
		System.out.println("this.jdbcUsername==>>>"+this.jdbcUsername);
		System.out.println("this.jdbcPassword==>>>"+this.jdbcPassword);
		
		
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setMaxActive(this.jdbcMaxActive);
		dataSource.setMaxIdle(this.jdbcMaxIdle);
		dataSource.setMinIdle(this.jdbcMinIdle);
		dataSource.setMaxWait(this.jdbcMaxWait);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactoryBean(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sqlmaps/*.xml"));
				
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory){
		
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		return sqlSessionTemplate;
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
		return dataSourceTransactionManager;
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.spring.www");
		return mapperScannerConfigurer;
	}
}
