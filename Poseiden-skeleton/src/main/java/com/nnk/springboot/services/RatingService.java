package com.nnk.springboot.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	
	@Autowired
	private RatingRepository ratingRepository;

	public ArrayList<Rating> findAll() {
		return ratingRepository.findAll();
	}

	public @Valid Rating save(@Valid Rating rating) {
		return ratingRepository.save(rating);
	}

	public Rating findById(Integer id) {
		return ratingRepository.findById(id).get();
	}

	public void deleteById(Integer id) {
		ratingRepository.deleteById(id);
	}

}
