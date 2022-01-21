package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepositoryMock;

	@Test
	public void testFindAll() throws Exception {
		// GIVEN
		User u1 = new User();
		User u2 = new User();
		ArrayList<User> us = new ArrayList<User>();
		us.add(u1);
		us.add(u2);

		// WHEN
		when(userRepositoryMock.findAll()).thenReturn(us);
		userService.setUserRepository(userRepositoryMock);

		// THEN
		assertThat(userService.findAll().size()).isEqualTo(2);
	}

	@Test
	public void testSave() throws Exception {
		// GIVEN
		User u1 = new User();
		u1.setFullname("toto");

		// WHEN
		when(userRepositoryMock.save(u1)).thenReturn(u1);
		userService.setUserRepository(userRepositoryMock);

		// THEN
		assertThat(userService.save(u1).getFullname()).isEqualTo("toto");
	}

	@Test
	public void testFindById() throws Exception {
		// GIVEN
		User u1 = new User();
		u1.setFullname("toto");
		Optional<User> uo = Optional.of(u1);

		// WHEN
		when(userRepositoryMock.findById(any(Integer.class))).thenReturn(uo);
		userService.setUserRepository(userRepositoryMock);

		// THEN
		assertThat(userService.findById(77).getFullname()).isEqualTo("toto");
	}

	@Test
	public void testDeleteById() throws Exception {
		// GIVEN
		User u1 = new User();
		u1.setFullname("toto");
		Optional<User> uo = Optional.of(u1);

		// WHEN
		when(userRepositoryMock.findById(any(Integer.class))).thenReturn(uo);
		userService.setUserRepository(userRepositoryMock);

		// THEN
		assertThat(userService.deleteById(77).getFullname()).isEqualTo("toto");
	}
	
	@Test
	public void testFindByUsername() throws Exception {
		// GIVEN
		User u1 = new User();
		u1.setFullname("toto");
		Optional<User> uo = Optional.of(u1);

		// WHEN
		when(userRepositoryMock.findByUsername(any(String.class))).thenReturn(uo);
		userService.setUserRepository(userRepositoryMock);

		// THEN
		assertThat(userService.findByUsername("toto")).isEqualTo(uo);
	}

}
