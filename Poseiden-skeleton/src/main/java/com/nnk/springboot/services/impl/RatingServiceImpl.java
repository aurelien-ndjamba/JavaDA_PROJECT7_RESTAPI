package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;

/**
 * Service RatingService
 */
@Service
public class RatingServiceImpl implements IRatingService{
	
	@Autowired
	private RatingRepository ratingRepository;
 
	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	@Override
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}

	@Override
	public @Valid Rating save(@Valid Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public Rating findById(Integer id) {
		return ratingRepository.findById(id).get();
	}

	@Override
	public Rating deleteById(Integer id) {
		Rating result = ratingRepository.findById(id).get();
		ratingRepository.deleteById(id);
		return result;
	}

}
