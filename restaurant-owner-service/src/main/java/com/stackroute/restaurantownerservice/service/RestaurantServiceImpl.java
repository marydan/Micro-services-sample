package com.stackroute.restaurantownerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.restaurantownerservice.domain.Restaurant;
import com.stackroute.restaurantownerservice.exception.RestaurantAlreadyExistsException;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFoundException;
import com.stackroute.restaurantownerservice.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
		Optional<Restaurant> restaurantExists = restaurantRepository.findById(restaurant.getId());
		if(restaurantExists.isPresent()) {
			throw new RestaurantAlreadyExistsException();
		}
		else {
			return restaurantRepository.save(restaurant);
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurantExists = restaurantRepository.findById(restaurant.getId());
		if(restaurantExists.isPresent()) {
			return restaurantRepository.save(restaurant);
		}
		else {
			throw new RestaurantNotFoundException();
		}
	}

	@Override
	public Restaurant getRestaurantById(String id) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurantExists = restaurantRepository.findById(id);
		if(restaurantExists.isPresent()) {
			return restaurantExists.get();
		}
		else {
			throw new RestaurantNotFoundException();
		}
	}

	@Override
	public Object deleteRestaurant(String id) throws RestaurantNotFoundException {
		Optional<Restaurant> restaurantExists = restaurantRepository.findById(id);
		if(restaurantExists.isPresent()) {
			 restaurantRepository.delete(restaurantExists.get());
			 return null;
		}
		else {
			throw new RestaurantNotFoundException();
		}
	}

}
