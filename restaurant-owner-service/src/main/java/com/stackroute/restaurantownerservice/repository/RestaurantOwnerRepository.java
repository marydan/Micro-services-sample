package com.stackroute.restaurantownerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.restaurantownerservice.domain.RestaurantOwner;

public interface RestaurantOwnerRepository extends MongoRepository<RestaurantOwner, String> {

}
