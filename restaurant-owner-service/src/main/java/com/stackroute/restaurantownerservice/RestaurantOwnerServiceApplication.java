package com.stackroute.restaurantownerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableFeignClients
public class RestaurantOwnerServiceApplication {
	
	
	@Bean
	  @LoadBalanced
	    public RestTemplate restTemplate(RestTemplateBuilder builder) {
	        return builder.build();
	    }

	public static void main(String[] args) {
		SpringApplication.run(RestaurantOwnerServiceApplication.class, args);
	}

}
