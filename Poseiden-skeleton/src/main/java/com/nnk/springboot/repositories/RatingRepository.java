package com.nnk.springboot.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
	ArrayList<Rating> findAll();
}
