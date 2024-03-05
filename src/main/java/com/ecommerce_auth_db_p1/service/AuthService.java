package com.ecommerce_auth_db_p1.service;

import com.ecommerce_auth_db_p1.entity.AuthResponse;
import com.ecommerce_auth_db_p1.entity.LoginRequest;
import com.ecommerce_auth_db_p1.entity.Users;

public interface AuthService {

	public abstract AuthResponse login(LoginRequest request);
	public abstract AuthResponse register(Users request);
}
