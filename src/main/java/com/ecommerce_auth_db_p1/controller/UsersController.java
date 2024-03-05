package com.ecommerce_auth_db_p1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce_auth_db_p1.entity.Users;
import com.ecommerce_auth_db_p1.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("/")
	public List<Users> listaUsuarios(){
		return usersService.getListUsers();
	}
}
