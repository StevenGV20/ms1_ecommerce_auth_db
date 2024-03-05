package com.ecommerce_auth_db_p1.service;

import java.security.Key;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce_auth_db_p1.entity.Users;


public interface JwtService {
	
	public abstract String getToken(Users user);

	public abstract String getToken(Map<String,Object> extraClaims, Users user);

	public abstract Key getKey();

	public abstract String getUsernameFromToken(String token);

	public abstract boolean isTokenValid(String token, UserDetails users);

}
