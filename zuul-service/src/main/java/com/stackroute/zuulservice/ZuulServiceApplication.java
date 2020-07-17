package com.stackroute.zuulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.stackroute.zuulservice.filter.JWTFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}
	
	
//	  @Bean public FilterRegistrationBean jwtFilter() { FilterRegistrationBean
//	  filterRegistrationBean = new FilterRegistrationBean();
//	  filterRegistrationBean.setFilter(new JWTFilter());
//	  filterRegistrationBean.addUrlPatterns("/user-service/api/v1/user/*","/restaurant-owner-service/api/v1/restaurantowner/*","/restaurant-owner-service/api/v1/orders/*");
//	  return filterRegistrationBean;
//	  }
	 
}