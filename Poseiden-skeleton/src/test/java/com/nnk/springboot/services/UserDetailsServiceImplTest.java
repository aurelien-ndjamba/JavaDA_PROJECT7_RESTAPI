package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.UserDetailsServiceImpl;
import com.nnk.springboot.services.impl.UserServiceImpl;

@SpringBootTest
public class UserDetailsServiceImplTest {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Mock
	private UserServiceImpl userServiceMock;

	@Test
	public void loadUserByUsernameTest() {

		// GIVEN
		String email = "toto@gmail.com";
		User user = new User(7, "toto", "C@meroun7", "totolebo", "ADMIN");
		Optional<User> optionalUser = Optional.of(user);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

		// WHEN
		when(userServiceMock.findByUsername(email)).thenReturn(optionalUser);
		userDetailsServiceImpl.setUserService(userServiceMock);

		// THEN
		assertThat(userDetailsServiceImpl.loadUserByUsername(email)).isEqualTo(u);

	}

}
