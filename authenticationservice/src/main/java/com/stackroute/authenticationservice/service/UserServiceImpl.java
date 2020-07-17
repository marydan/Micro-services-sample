package com.stackroute.authenticationservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		
		User user = userRepository.findByUsernameAndPassword(username, password);
		System.out.println("user found:::" + user);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
		
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException {
		User registeredUser = null;
		Optional<User> userExists = userRepository.findById(user.getId());
		if(userExists.isPresent()) {
			throw new UserAlreadyExistsException();
			
		}
		registeredUser = userRepository.save(user);
		return registeredUser;
	}

}
