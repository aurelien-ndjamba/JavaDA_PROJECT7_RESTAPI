package com.nnk.springboot.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public ArrayList<User> findAll() {
		return userRepository.findAll();
	}

	public @Valid User save(@Valid User user) {
		return userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

}
