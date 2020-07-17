package com.stackroute.authenticationservice.service;

import java.util.Map;

import com.stackroute.authenticationservice.domain.User;

public interface ISecurityTokenGenerator {
	
	Map<String, String> generateJWTToken(User user);

}
