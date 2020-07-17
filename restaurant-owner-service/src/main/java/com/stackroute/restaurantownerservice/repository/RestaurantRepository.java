package com.stackroute.restaurantownerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.restaurantownerservice.domain.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

}
