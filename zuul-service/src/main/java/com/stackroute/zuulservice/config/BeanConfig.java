package com.stackroute.zuulservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stackroute.zuulservice.filter.ErrorFilter;
import com.stackroute.zuulservice.filter.PostFilter;
import com.stackroute.zuulservice.filter.PreFilter;
import com.stackroute.zuulservice.filter.RouterFilter;

@Configuration
public class BeanConfig {
	
	//PreFilter
	//PostFilter
	//routerFilter
	//ErrorFilter
	
	@Bean
	public PreFilter preFilter() {
		System.out.println("prefilter");
		return new PreFilter();
	}
	
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	
	@Bean
	public RouterFilter routerFilter() {
		return new RouterFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

}
