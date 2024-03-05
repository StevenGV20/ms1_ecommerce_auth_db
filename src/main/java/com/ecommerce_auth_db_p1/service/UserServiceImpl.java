package com.ecommerce_auth_db_p1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_auth_db_p1.entity.Users;
import com.ecommerce_auth_db_p1.repository.UsersRepository;

@Service
public class UserServiceImpl implements UsersService{
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public List<Users> getListUsers() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<Users> getUser(Long id) {
		return usersRepository.findById(id);
	}

	@Override
	public Users saveUser(Users user) {
		return usersRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

	@Override
	public List<Users> getListUsersByRol(Long rol) {
		return usersRepository.findByRol(rol);
	}

}
