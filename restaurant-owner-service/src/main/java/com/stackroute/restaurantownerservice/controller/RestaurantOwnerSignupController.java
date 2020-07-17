package com.stackroute.restaurantownerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.restaurantownerservice.domain.RestaurantOwner;
import com.stackroute.restaurantownerservice.dto.RegisterUser;
import com.stackroute.restaurantownerservice.exception.RestaurantOwnerAlreadyExistsException;
import com.stackroute.restaurantownerservice.service.IRestaurantOwnerService;

@RestController
@RequestMapping("api/auth/v1/owner")
public class RestaurantOwnerSignupController {
	
	@Autowired
	private IRestaurantOwnerService restaurantOwnerService;
	private ResponseEntity<?> responseEntity;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String AUTHENTICATION_SERVICE_URL = "http://authentication-service/api/v1/auth/user";
	

	
	@PostMapping("/signup")
	@HystrixCommand(groupKey = "signup", commandKey="saveUser", fallbackMethod="authServiceFallBack_SignUpUser")
	public ResponseEntity<?> saveUser(@RequestBody RestaurantOwner restaurantOwner) throws RestaurantOwnerAlreadyExistsException{
		try {
			RestaurantOwner createdRestaurantOwner = restaurantOwnerService.registerOwner(restaurantOwner);
			responseEntity = new ResponseEntity<>(createdRestaurantOwner, HttpStatus.CREATED);
		
			RegisterUser userToBeRegistered = new RegisterUser();
			userToBeRegistered.setUsername(restaurantOwner.getUsername());
			userToBeRegistered.setPassword(restaurantOwner.getPassword());
			userToBeRegistered.setRole("owner");
			
			HttpEntity<RegisterUser> requestEntity = new HttpEntity<>(userToBeRegistered);
			//restTemplate.exchange(USER_SERVICE_URL +"/order",HttpMethod.POST,requestEntity, Boolean.class);
			RegisterUser registeredUserInAuthenticationService = restTemplate.postForObject(AUTHENTICATION_SERVICE_URL + "/register", requestEntity, RegisterUser.class);
			
			if(registeredUserInAuthenticationService != null)
			responseEntity = new ResponseEntity<>(createdRestaurantOwner, HttpStatus.CREATED);
			else
			responseEntity = new ResponseEntity<>("some internal error occured", HttpStatus.INTERNAL_SERVER_ERROR);
		
		}catch(RestaurantOwnerAlreadyExistsException e) {
			System.out.println("Exception thrownn......");
			throw e;
		}
		
		catch (Exception e) {
		//	System.out.println();
			e.printStackTrace();
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	  public ResponseEntity<?> authServiceFallBack_SignUpUser(RestaurantOwner
	  restaurantOwner) { 
		  System.out.println("Message from AuthService : authService_errorMessage"); 
		  return new ResponseEntity<>
	  ("Authentication service is down...Please try after some time...",
	  HttpStatus.GATEWAY_TIMEOUT); }
	 
}
