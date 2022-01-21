package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.BidList;

/**
 * Interface IBidListService
 */
public interface IBidListService {

	/**
	 * Méthode pour lister tous les objets 'BidList' dans la base de donnée
	 * 
	 * @return List<BidList>
	 */
	public List<BidList> findAll();

	/**
	 * Méthode pour ajouter un objet 'BidList' dans la base de donnée.
	 * 
	 * @param BidList
	 * @return @Valid BidList
	 */
	public @Valid BidList save(@Valid BidList bid);

	/**
	 * Méthode pour obtenir un objet 'BidList' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return BidList
	 */
	public BidList findById(Integer id);

	
	/**
	 * Méthode pour supprimer un objet 'BidList' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return BidList
	 */
	public BidList deleteById(Integer id);

}
