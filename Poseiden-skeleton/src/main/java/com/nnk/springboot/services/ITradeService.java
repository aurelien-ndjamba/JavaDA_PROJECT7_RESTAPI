package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.Trade;

/**
 * Interface ITradeService
 */
public interface ITradeService {

	/**
	 * Méthode pour lister tous les objets 'Trade' dans la base de donnée
	 * 
	 * @return List<Trade>
	 */
	public List<Trade> findAll();

	/**
	 * Méthode pour ajouter un objet 'Trade' dans la base de donnée
	 * 
	 * @param Trade
	 * @return @Valid Trade
	 */
	public @Valid Trade save(@Valid Trade trade);

	/**
	 * Méthode pour obtenir un objet 'Trade' à partir de sa clé primaire 'id'
	 * dans la base de donnée
	 * 
	 * @param Integer
	 * @return Trade
	 */
	public Trade findById(Integer id);

	/**
	 * Méthode pour supprimer un objet 'Trade' dans la base de donnée
	 * 
	 * @param Integer
	 * @return Trade
	 */
	public Trade deleteById(Integer id);

}
