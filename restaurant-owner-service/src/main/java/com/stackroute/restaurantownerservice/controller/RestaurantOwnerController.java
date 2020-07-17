package com.stackroute.restaurantownerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.restaurantownerservice.domain.Restaurant;
import com.stackroute.restaurantownerservice.exception.RestaurantAlreadyExistsException;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;
import com.stackroute.restaurantownerservice.service.IRestaurantOwnerService;
import com.stackroute.restaurantownerservice.service.IRestaurantService;

@RestController
@RequestMapping("api/v1/restaurantowner")
public class RestaurantOwnerController {
	
	@Autowired
	private IRestaurantService restaurantService;
	
	@Autowired
	private IRestaurantOwnerService restaurantOwnerService;
	private ResponseEntity<?> responseEntity;
	
	
	
	
	
	@PostMapping("/restaurant")
	public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException{
		try {
			Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
			responseEntity = new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
		}catch(RestaurantAlreadyExistsException e) {
			throw e;
		}
		
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping("/restaurant")
	public ResponseEntity<?> getAllRestaurants(){
		try {
			responseEntity = new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<?> getRestaurantById(@PathVariable("id") String id) throws RestaurantNotFoundException{
		try {
			responseEntity = new ResponseEntity<>(restaurantService.getRestaurantById(id), HttpStatus.OK);
		} 
		catch(RestaurantNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping("/restaurant")
	public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant) throws RestaurantNotFoundException{
		try {
			responseEntity = new ResponseEntity<>(restaurantService.updateRestaurant(restaurant), HttpStatus.OK);
		} 
		catch(RestaurantNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
		
	}

	
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable("id") String id) throws RestaurantNotFoundException{
		try {
			responseEntity = new ResponseEntity<>(restaurantService.deleteRestaurant(id), HttpStatus.OK);
		} 
		catch(RestaurantNotFoundException e) {
			throw e;
		}
		catch (Exception e) {
			responseEntity = new ResponseEntity<>("Some internal error occured..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
}
