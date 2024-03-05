package com.ecommerce_auth_db_p1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_auth_db_p1.entity.Rol;
import com.ecommerce_auth_db_p1.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public Rol saveRol(Rol rol) {
		return rolRepository.save(rol);
	}

	@Override
	public Optional<Rol> getRol(Long id) {
		return rolRepository.findById(id);
	}

	@Override
	public List<Rol> getListRol() {
		return rolRepository.findAll();
	}

	@Override
	public void deleteRol(Long id) {
		rolRepository.deleteById(id);
	}

	
}
