package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.nnk.springboot.domain.User;

public interface IUserService {

	public List<User> findAll();

	public @Valid User save(@Valid User user);

	public User findById(Integer id);

	public Optional<User> findByUsername(String username);

	public User deleteById(Integer id);

	public User delete(User user);

}
