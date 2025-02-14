package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Rating;

/**
 * repository "RatingRepository"
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
