package com.ecommerce_auth_db_p1.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce_auth_db_p1.entity.Rol;

public interface RolService {
	
	public abstract Rol saveRol(Rol rol);
	public abstract Optional<Rol> getRol(Long id);
	public abstract List<Rol> getListRol();
	public abstract void deleteRol(Long id);
	
}
