package com.nnk.springboot.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
	ArrayList<Rating> findAll();
}
