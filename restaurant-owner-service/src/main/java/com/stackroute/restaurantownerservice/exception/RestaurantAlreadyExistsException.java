package com.stackroute.restaurantownerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant already exists with this id")
public class RestaurantAlreadyExistsException extends Exception {

}
