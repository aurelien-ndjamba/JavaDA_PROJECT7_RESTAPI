package com.nnk.springboot.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Service UserDetailsServiceImpl
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired  
	UserServiceImpl userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		com.nnk.springboot.domain.User user = userService.findByUsername(email).get();
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

}
