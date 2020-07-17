package com.stackroute.restaurantownerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason ="Restaurant Owner not exists with this ID")
public class RestaurantOwnerNotFoundException extends Exception {

}
