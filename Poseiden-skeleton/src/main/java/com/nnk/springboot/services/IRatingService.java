package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.Rating;

/**
 * Interface IRatingService
 */
public interface IRatingService {
	
	/**
	 * Méthode pour lister tous les objets 'Rating' dans la base de donnée
	 * 
	 * @return List<Rating>
	 */
	public List<Rating> findAll();

	/**
	 * Méthode pour ajouter un objet 'Rating' dans la base de donnée
	 * 
	 * @param Rating
	 * @return @Valid Rating
	 */
	public @Valid Rating save(@Valid Rating rating);

	/**
	 * Méthode pour obtenir un objet 'Rating' à partir de sa clé primaire 'id' dans la base de donnée
	 * 
	 * @param Integer
	 * @return Rating
	 */
	public Rating findById(Integer id);

	/**
	 * Méthode pour supprimer un objet 'Rating' dans la base de donnée
	 * 
	 * @param Integer
	 * @return Rating
	 */
	public Rating deleteById(Integer id);

}
