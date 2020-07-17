package com.stackroute.authenticationservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.service.IUserService;
import com.stackroute.authenticationservice.service.JWTTokenGeneratorService;

@RestController
@RequestMapping("api/v1/auth/user")
public class UserAuthenticationController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private JWTTokenGeneratorService securityTokenGenerator;
	
	private ResponseEntity<?> responseEntity;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException{
		Map<String, String> map = null;
	    try {
	    	System.out.println(user.getUsername() +""+ user.getPassword());
	      User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	     System.out.println("UserObj::" + userObj.getUsername());
	      if (userObj.getUsername().equals(user.getUsername())) {
	        map = securityTokenGenerator.generateJWTToken(user);
	      }
	      responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
	    }
	  catch(UserNotFoundException e){
	      throw new UserNotFoundException();
	  }
	    catch (Exception e){
	    	//System.out.println(e.printStackTrace(););
	    	//e.printStackTrace();
	      responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    return responseEntity;
	  }
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws UserAlreadyExistsException {
		User registeredUser = null;
		try {
			registeredUser = userService.registerUser(user);
			System.out.println("REgistered User in Authentication Service Success-->" + registeredUser);
		} catch (UserAlreadyExistsException e) {
			throw e;
		}catch(Exception e) {
			return null;
		}
		return registeredUser;
	}
}