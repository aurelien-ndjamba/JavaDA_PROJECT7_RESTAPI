package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired  
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		com.nnk.springboot.domain.User User = userService.findByUsername(email).get();
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
			authorities.add(new SimpleGrantedAuthority(User.getRole()));
		System.out.println(new User(User.getUsername(), User.getPassword(), authorities));
		return new User(User.getUsername(), User.getPassword(), authorities);
	}

}
