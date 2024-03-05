package com.ecommerce_auth_db_p1.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecommerce_auth_db_p1.entity.Rol;
import com.ecommerce_auth_db_p1.repository.RolRepository;
import com.ecommerce_auth_db_p1.service.JwtService;
import com.ecommerce_auth_db_p1.service.UsersService;

import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UsersService usersService;
	@Autowired
    private UserDetailsService userDetailsService;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	       
        final String token = getTokenFromRequest(request);
        final String username;

        if (token==null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        username=jwtService.getUsernameFromToken(token);

        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            
            if (jwtService.isTokenValid(token, userDetails))
            {
            	// Obtener los roles del usuario desde la base de datos
                //Optional<Rol> roles = rolRepository.findById(usersService.getUserByUsername(username).get().getRol().getRol_id());
                Rol rolobj = entityManager.find(Rol.class, usersService.getUserByUsername(username).get().getRol().getRol_id());
                List<Rol> roles  = new ArrayList<>();
                roles.add(rolobj);
                // Convertir los roles en autoridades
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                        .collect(Collectors.toList());
                System.out.println("userDetails: "+userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities);

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        
        filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}

	
}
