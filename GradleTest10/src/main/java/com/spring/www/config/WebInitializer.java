package com.spring.www.config;



import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected java.lang.Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ ModuleConfig.class};
//		return null;
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { WebConfig.class }; 
	}
	
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		  return new String[] { "/" }; 
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8"); 
		
		return new Filter[] { characterEncodingFilter}; 
	}
}
