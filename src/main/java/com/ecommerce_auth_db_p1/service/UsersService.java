package com.ecommerce_auth_db_p1.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce_auth_db_p1.entity.Users;

public interface UsersService {

	public abstract List<Users> getListUsers();
	public abstract Optional<Users> getUser(Long id);
	public abstract Users saveUser(Users user);
	public abstract void deleteUser(Long id);
	public abstract List<Users> getListUsersByRol(Long rol);
	public abstract Optional<Users> getUserByUsername(String username);
	
}
