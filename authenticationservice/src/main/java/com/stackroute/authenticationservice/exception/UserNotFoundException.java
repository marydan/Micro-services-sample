package com.stackroute.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="No user exists with this username and password")
public class UserNotFoundException extends Exception {

}
