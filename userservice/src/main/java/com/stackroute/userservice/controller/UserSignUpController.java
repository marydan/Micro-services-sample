package com.stackroute.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stackroute.userservice.dto.RegisterUser;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.IUserService;

@RestController
@RequestMapping("api/auth/v1/user")
public class UserSignUpController {
	@Autowired
	private IUserService userService;
	private ResponseEntity<?> responseEntity;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String AUTHENTICATION_SERVICE_URL = "http://authentication-service/api/v1/auth/user";
	

	@PostMapping("/signup")
	public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
		System.out.println("inside UserSignUp Controller..");
		try {
			User createdUser = userService.saveUser(user);
		
			//Register the user inside the authentication service
			
			RegisterUser userToBeRegistered = new RegisterUser();
			userToBeRegistered.setUsername(user.getUsername());
			userToBeRegistered.setPassword(user.getPassword());
			userToBeRegistered.setRole("user");
			
			HttpEntity<RegisterUser> requestEntity = new HttpEntity<>(userToBeRegistered);
			//restTemplate.exchange(USER_SERVICE_URL +"/order",HttpMethod.POST,requestEntity, Boolean.class);
			RegisterUser registeredUserInAuthenticationService = restTemplate.postForObject(AUTHENTICATION_SERVICE_URL + "/register", requestEntity, RegisterUser.class);
			
			if(registeredUserInAuthenticationService != null)
			responseEntity = new ResponseEntity<>(createdUser, HttpStatus.CREATED);
			else
			responseEntity = new ResponseEntity<>("some internal error occured", HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (UserAlreadyExistsException e) {
			throw e;
		} catch (Exception e) {
			System.out.println(e);
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}

	
}
