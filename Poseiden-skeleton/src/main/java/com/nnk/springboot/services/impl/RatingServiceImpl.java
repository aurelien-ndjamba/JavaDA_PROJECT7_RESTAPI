package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;

/**
 * Service RatingService respectant le contrat dénifi par IRatingService.
 */
@Service
public class RatingServiceImpl implements IRatingService{
	
	@Autowired
	private RatingRepository ratingRepository;
 
	/**
	 * Setter de RatingRepository. 
	 * 
	 * @param RatingRepository
	 * @return void
	 */
	public void setRatingRepository(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}
	
	/**
	 * Méthode définie par IRatingService pour lister de tous les objets 'Rating' dans la base de donnée.
	 * 
	 * @return List<Rating>
	 */
	@Override
	public List<Rating> findAll() {
		return ratingRepository.findAll();
	}

	/**
	 * Méthode définie par IRatingService pour ajouter un objet 'Rating' dans la base de donnée.
	 * 
	 * @param Rating
	 * @return @Valid Rating
	 */
	@Override
	public @Valid Rating save(@Valid Rating rating) {
		return ratingRepository.save(rating);
	}

	/**
	 * Méthode définie par IRatingService pour obtenir un objet 'Rating' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return Rating
	 */
	@Override
	public Rating findById(Integer id) {
		return ratingRepository.findById(id).get();
	}

	/**
	 * Méthode définie par IRatingService pour supprimer une objet 'Rating' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return Rating
	 */
	@Override
	public Rating deleteById(Integer id) {
		Rating result = ratingRepository.findById(id).get();
		ratingRepository.deleteById(id);
		return result;
	}

}
