package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.RuleName;

/**
 * Interface IRuleNameService
 */
public interface IRuleNameService {

	/**
	 * Méthode pour lister tous les objets 'RuleName' dans la base de donnée
	 * 
	 * @return List<RuleName>
	 */
	public List<RuleName> findAll();

	/**
	 * Méthode pour ajouter un objet 'RuleName' dans la base de donnée
	 * 
	 * @param RuleName
	 * @return @Valid RuleName
	 */
	public @Valid RuleName save(@Valid RuleName ruleName);

	/**
	 * Méthode pour obtenir un objet 'RuleName' à partir de sa clé primaire 'id' dans la base de donnée
	 * 
	 * @param Integer
	 * @return RuleName
	 */
	public RuleName findById(Integer id);

	/**
	 * Méthode pour supprimer un objet 'RuleName' dans la base de donnée
	 * 
	 * @param Integer
	 * @return RuleName
	 */
	public RuleName deleteById(Integer id);

}
