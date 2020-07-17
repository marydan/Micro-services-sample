package com.stackroute.authenticationservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.authenticationservice.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTTokenGeneratorService implements ISecurityTokenGenerator {

	@Override
	public Map<String, String> generateJWTToken(User user) {
		String jwtToken = null;
	    jwtToken = Jwts.builder().
	    		        setSubject(user.getUsername()).
	    		        setIssuedAt(new Date())
	    		        .claim("name", user.getUsername())
	    		        .claim("role", user.getRole())
	      .signWith(SignatureAlgorithm.HS256,"secretkey").compact();

	    Map<String,String> map = new HashMap<>();
	    map.put("token",jwtToken);
	    map.put("message", "User Successfully logged in");
	    return map;
	}

}
