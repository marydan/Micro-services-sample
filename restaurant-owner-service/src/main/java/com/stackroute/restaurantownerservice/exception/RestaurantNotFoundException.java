package com.stackroute.restaurantownerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Restaurant doesn't exists with this id")
public class RestaurantNotFoundException extends Exception {

}
