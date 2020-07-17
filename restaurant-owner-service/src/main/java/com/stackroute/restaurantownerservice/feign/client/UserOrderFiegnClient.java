package com.stackroute.restaurantownerservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stackroute.restaurantownerservice.dto.UserOrder;

@FeignClient("user-service/api/v1/user")
public interface UserOrderFiegnClient {
	
	@PostMapping("/order")
	UserOrder updateUserOrder(@RequestBody UserOrder userOrder);
	

}
