package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;

public interface IUserService {

	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;
	public User registerUser(User user) throws UserAlreadyExistsException;
}
