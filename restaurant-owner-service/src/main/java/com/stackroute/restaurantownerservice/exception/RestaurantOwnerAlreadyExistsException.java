package com.stackroute.restaurantownerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant Owner already exists with this id")
public class RestaurantOwnerAlreadyExistsException extends Exception {

}
