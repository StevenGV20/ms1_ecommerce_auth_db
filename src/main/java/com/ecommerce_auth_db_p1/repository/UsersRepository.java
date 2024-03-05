package com.ecommerce_auth_db_p1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce_auth_db_p1.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	public List<Users> findByRol(Long rol);
}
