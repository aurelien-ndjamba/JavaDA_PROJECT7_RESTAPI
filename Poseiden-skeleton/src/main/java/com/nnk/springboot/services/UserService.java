package com.nnk.springboot.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Service UserService
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ArrayList<User> findAll() {
		return userRepository.findAll();
	}

	public @Valid User save(@Valid User user) {
		return userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User deleteById(Integer id) {
		User result = userRepository.findById(id).get();
		userRepository.deleteById(id);
		return result;
	}

}
