package com.ecommerce_auth_db_p1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce_auth_db_p1.entity.AuthResponse;
import com.ecommerce_auth_db_p1.entity.LoginRequest;
import com.ecommerce_auth_db_p1.entity.Users;
import com.ecommerce_auth_db_p1.repository.RolRepository;
import com.ecommerce_auth_db_p1.repository.UsersRepository;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		System.out.println("username: " +request.getUsername());
		Users userDetails  = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token = jwtService.getToken(userDetails);
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(token);
		authResponse.setLastname(userDetails.getLastname());
		authResponse.setName(userDetails.getName());
		authResponse.setUsername(userDetails.getUsername());
		return authResponse;
	}

	@Override
	public AuthResponse register(Users request) {
		/*Optional<Rol> rol = rolRepository.findById(Long.parseLong("1"));
		request.setRol(rol.get());*/

		request.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepository.save(request);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setToken(jwtService.getToken(request));
		authResponse.setLastname(request.getLastname());
		authResponse.setName(request.getName());
		authResponse.setUsername(request.getUsername());
		
		return authResponse;
	}

}
