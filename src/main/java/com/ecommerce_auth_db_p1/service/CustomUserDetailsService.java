package com.ecommerce_auth_db_p1.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce_auth_db_p1.entity.Rol;
import com.ecommerce_auth_db_p1.entity.Users;
import com.ecommerce_auth_db_p1.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Rol> roleIds = new ArrayList<>();
        for(Users user : userRepository.findRolIdByUsername(username)) {
        	roleIds.add(user.getRol());
        }
        
        Collection<GrantedAuthority> authorities = roleIds.stream()
            .map(roleId -> new SimpleGrantedAuthority("ROLE_" + roleId.getName()))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, "123456", authorities);
    }
}
