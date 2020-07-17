package com.stackroute.restaurantownerservice.service;

import java.util.List;

import com.stackroute.restaurantownerservice.domain.Restaurant;
import com.stackroute.restaurantownerservice.exception.RestaurantAlreadyExistsException;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;

public interface IRestaurantService {
	
	public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;
	public List<Restaurant> getAllRestaurants();
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException;
	public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException;
	public Object deleteRestaurant(String id) throws RestaurantNotFoundException;

}
