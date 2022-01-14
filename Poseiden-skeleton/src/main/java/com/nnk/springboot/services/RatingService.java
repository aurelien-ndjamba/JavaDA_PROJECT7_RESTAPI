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

	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	public Rating findById(Integer id) {
		return ratingRepository.findById(id).get();
	}

	public Rating deleteById(Integer id) {
		Rating result = ratingRepository.findById(id).get();
		ratingRepository.deleteById(id);
		return result;
	}

}
