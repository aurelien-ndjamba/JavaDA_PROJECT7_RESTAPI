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
 * Service UserDetailsServiceImpl respectant le contrat dénifi par UserDetailsService.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired  
	UserServiceImpl userService;
	
	/**
	 * Méthode définie par UserDetailsService pour obtenir les détails d'un utilisateur à partir de son username.
	 * 
	 * @param String
	 * @return UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		com.nnk.springboot.domain.User user = userService.findByUsername(username).get();
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	/**
	 * Setter de UserService.
	 * 
	 * @param UserServiceImpl
	 * @return void
	 */
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

}
