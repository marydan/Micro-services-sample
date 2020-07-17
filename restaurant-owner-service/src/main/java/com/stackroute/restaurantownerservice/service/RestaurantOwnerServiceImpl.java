package com.stackroute.restaurantownerservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.restaurantownerservice.domain.Restaurant;
import com.stackroute.restaurantownerservice.domain.RestaurantOwner;
import com.stackroute.restaurantownerservice.exception.RestaurantAlreadyExistsException;
import com.stackroute.restaurantownerservice.exception.RestaurantOwnerAlreadyExistsException;
import com.stackroute.restaurantownerservice.repository.RestaurantOwnerRepository;

@Service
public class RestaurantOwnerServiceImpl implements IRestaurantOwnerService {
	
	@Autowired
	private RestaurantOwnerRepository restaurantOwnerRepository;

	@Override
	public RestaurantOwner registerOwner(RestaurantOwner owner) throws RestaurantOwnerAlreadyExistsException {
		Optional<RestaurantOwner> ownerExists = restaurantOwnerRepository.findById(owner.getId());
		if(ownerExists.isPresent()) {
			throw new RestaurantOwnerAlreadyExistsException();
		}
		else {
			return restaurantOwnerRepository.save(owner);
		}
	}

}
