package com.ecommerce_auth_db_p1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public List<Users> getUsers(){
		return usersService.getListUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Users>> getUsersById(@PathVariable Long id){
		Optional<Users> user = usersService.getUser(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Optional<Users>> deleteuser(@PathVariable Long id){
		Optional<Users> user = usersService.getUser(id);
		if(user.isPresent()) {
			usersService.deleteUser(id);
			return ResponseEntity.ok(user);
		}
		return ResponseEntity.notFound().build();
	}
}
