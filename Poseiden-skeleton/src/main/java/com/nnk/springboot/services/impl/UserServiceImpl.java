package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;

/**
 * Service UserService
 */
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public @Valid User save(@Valid User user) {
		return userRepository.save(user);
	}

	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User deleteById(Integer id) {
		User result = userRepository.findById(id).get();
		userRepository.deleteById(id);
		return result;
	}

	@Override
	public User delete(User user) {
		User result = new User();
		if (userRepository.existsById(user.getId())){
			result = userRepository.findById(user.getId()).get();
			userRepository.deleteById(user.getId());
		}
		return result;
	}

}
