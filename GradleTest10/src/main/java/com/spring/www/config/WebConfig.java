package com.spring.www.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.spring.www.controller.InterCeptorController;

@Configuration
@EnableWebMvc // <annotation-driven />
@ComponentScan(basePackages = { "com.spring.www.controller" })
public class WebConfig extends WebMvcConfigurerAdapter{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");	
		
		registry.addInterceptor(localeChangeInterceptor);
		
		// excludePathPatterns 제외 패턴
		registry.addInterceptor(new InterCeptorController()).addPathPatterns("/**").excludePathPatterns("/main/**");
		
		
	}
	/*
	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping(){
		
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		
		Properties props = new Properties();
		
		props.setProperty("/main", "InterCeptorController");
		System.out.println("SimpleUrlHandlerMapping");		
		simpleUrlHandlerMapping.setMappings(props);
		InterCeptorController[] interCeptorController = new InterCeptorController[1];
		interCeptorController[0] = new InterCeptorController();
		simpleUrlHandlerMapping.setInterceptors(interCeptorController);
		
		return simpleUrlHandlerMapping;
	}*/
	
	@Bean
	public LocaleResolver localeResolver(){
		
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(StringUtils.parseLocaleString("en"));
		return cookieLocaleResolver;
	}
	
	
	
	@Bean
	public ViewResolver viewResolver(){
		/*
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		*/
		
		
		
		
		VelocityViewResolver viewResolver = new VelocityViewResolver();		
		viewResolver.setViewClass(VelocityView.class);		
		System.out.println("VelocityViewResolverVelocityViewResolverVelocityViewResolver");
//		viewResolver.setPrefix("/WebContent/WEB-INF/views/");
		viewResolver.setSuffix(".vm");
		viewResolver.setCache(true);
		viewResolver.setContentType("text/html; charset=UTF-8");
//		viewResolver.setOrder(1);
		return viewResolver;
	}
	
	@Bean
	public VelocityConfigurer velocityConfigurer(){
	
		VelocityConfigurer velocityConfigurer = new VelocityConfigurer();			
		velocityConfigurer.setResourceLoaderPath("/WEB-INF/views/");
		
		Properties p = new Properties();
		p.setProperty("input.encoding", "UTF-8");
		p.setProperty("out.encoding", "UTF-8");
		velocityConfigurer.setVelocityProperties(p);
		
		
		return velocityConfigurer;
	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
	
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		Map<String, String> map = new HashMap<String, String>();
		map.put("json", "application/json");
		map.put("xml", "application/xml");
		map.put("html", "text/html");
		
		contentNegotiatingViewResolver.setMediaTypes(map);
		//contentNegotiatingViewResolver.setOrder(0);
		
		List<View> defaltViews = new ArrayList<View>();
		
		
		MappingJackson2JsonView jackson2JsonView = new MappingJackson2JsonView();
		jackson2JsonView.setContentType("application/json;charset=UTF-8");
		
		defaltViews.add(jackson2JsonView);
		
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAutodetectAnnotations(true);		
		MarshallingView marshallingView = new MarshallingView();
		marshallingView.setMarshaller(xStreamMarshaller);
		marshallingView.setContentType("application/xml;charset=UTF-8");
		//marshallingView.setModelKey("xmlData");	
		
		defaltViews.add(marshallingView);
		
		contentNegotiatingViewResolver.setDefaultViews(defaltViews);
		
		return contentNegotiatingViewResolver;
	}
	/*
	@Bean
	public MarshallingView marshallingView(){
	
		
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAutodetectAnnotations(true);		
		MarshallingView marshallingView = new MarshallingView();
		marshallingView.setMarshaller(xStreamMarshaller);
		marshallingView.setContentType("application/xml;charset=UTF-8");
		marshallingView.setModelKey("xmlData");
		
		return marshallingView;
	}*/
	/*
	@Bean
	public XStreamMarshaller xStreamMarshaller(){
	
		
		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAutodetectAnnotations(true);
		
		return xStreamMarshaller;
	}*/
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
	}
	
	
	@Bean
	public MessageSource messageSource(){
		
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}
	
	
	
}
