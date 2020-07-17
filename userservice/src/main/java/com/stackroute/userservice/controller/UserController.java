package com.stackroute.userservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Order;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.IUserService;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

	@Autowired
	private IUserService userService;
	private ResponseEntity<?> responseEntity;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	
	@GetMapping("/user")
	public ResponseEntity<?> getAllUsers() {
		try {
			List<User> allUsers = userService.getAllUsers();
			responseEntity = new ResponseEntity<>(allUsers, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") String id) throws UserNotFoundException {
		try {
			User user = userService.getUserById(id);
			responseEntity = new ResponseEntity<>(user, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody User user) throws UserNotFoundException{
		try {
			User updatedUser = userService.updateUser(user);
			
			responseEntity = new ResponseEntity<>(updatedUser, HttpStatus.OK);

		} catch (UserNotFoundException e) {
			throw e;
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	
	  @PostMapping("/order") public ResponseEntity<?> userOrder(@RequestBody Order
	  order){ System.out.println("order inside user:" + order) ;
	  
	  userService.saveUserOrder(order); responseEntity = new
	  ResponseEntity<>(true,HttpStatus.OK); return responseEntity; }
	 
}
