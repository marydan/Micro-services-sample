package com.stackroute.restaurantownerservice.service;

import com.stackroute.restaurantownerservice.domain.RestaurantOwner;
import com.stackroute.restaurantownerservice.exception.RestaurantOwnerAlreadyExistsException;

public interface IRestaurantOwnerService {
	
	public RestaurantOwner registerOwner(RestaurantOwner owner) throws RestaurantOwnerAlreadyExistsException;

}
