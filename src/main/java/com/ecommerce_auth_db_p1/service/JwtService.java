package com.ecommerce_auth_db_p1.service;

import java.security.Key;
import java.util.Map;

import com.ecommerce_auth_db_p1.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {
	
	public abstract String getToken(Users user);

	public abstract String getToken(Map<String,Object> extraClaims, Users user);

	public abstract Key getKey();

	public abstract String getUsernameFromToken(String token);

	public abstract boolean isTokenValid(String token, UserDetails users);

}
