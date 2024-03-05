package com.ecommerce_auth_db_p1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce_auth_db_p1.entity.Rol;
import com.ecommerce_auth_db_p1.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	public List<Users> findByRol(Rol rol);
	public Optional<Users> findByUsername(String username);
	@Query("SELECT u.rol FROM Users u WHERE u.username = :username")
	public List<Users> findRolIdByUsername(String username);
}
