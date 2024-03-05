package com.ecommerce_auth_db_p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_auth_db_p1.entity.AuthResponse;
import com.ecommerce_auth_db_p1.entity.LoginRequest;
import com.ecommerce_auth_db_p1.entity.Users;
import com.ecommerce_auth_db_p1.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody Users request) {
		
		return ResponseEntity.ok(authService.register(request));
	}
}
