package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.Rating;

public interface IRatingService {
	
	public List<Rating> findAll();

	public @Valid Rating save(@Valid Rating rating);

	public Rating findById(Integer id);

	public Rating deleteById(Integer id);

}
