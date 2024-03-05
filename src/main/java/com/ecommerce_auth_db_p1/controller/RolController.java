package com.ecommerce_auth_db_p1.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecommerce_auth_db_p1.entity.Rol;
import com.ecommerce_auth_db_p1.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping("/")
	public ResponseEntity<List<Rol>> listRoles() {
		return ResponseEntity.ok(rolService.getListRol());
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> addRol(@RequestBody Rol rol, UriComponentsBuilder ucb){
		Rol rolCreated = rolService.saveRol(rol);
		URI locationOfNewRolCreated = ucb
				.path("rol/{id}")
				.buildAndExpand(rolCreated.getRol_id())
				.toUri();
		return ResponseEntity.created(locationOfNewRolCreated).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Rol>> getRolById(@PathVariable Long id) {
		Optional<Rol> rol = rolService.getRol(id);
		if(rol.isPresent()) {
			return ResponseEntity.ok(rol);			
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
