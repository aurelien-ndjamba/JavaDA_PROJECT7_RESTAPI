package com.nnk.springboot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.IRuleNameService;

/**
 * Service RuleNameService respectant le contrat dénifi par IRuleNameService.
 */
@Service
public class RuleNameServiceImpl implements IRuleNameService{
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

	/**
	 * Setter de RuleNameRepository. 
	 * 
	 * @param RuleNameRepository
	 * @return void
	 */
	public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	/**
	 * Méthode définie par IRuleNameService pour lister de tous les objets 'RuleName' dans la base de donnée.
	 * 
	 * @return List<RuleName>
	 */
	@Override
	public List<RuleName> findAll() {
		return ruleNameRepository.findAll();
	}

	/**
	 * Méthode définie par IRuleNameService pour ajouter un objet 'RuleName' dans la base de donnée.
	 * 
	 * @param RuleName
	 * @return @Valid RuleName
	 */
	@Override
	public RuleName save(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	/**
	 * Méthode définie par IRuleNameService pour obtenir un objet 'RuleName' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return RuleName
	 */
	@Override
	public RuleName findById(Integer id) {
		return ruleNameRepository.findById(id).get();
	}

	/**
	 * Méthode définie par IRuleNameService pour supprimer une objet 'RuleName' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return RuleName
	 */
	@Override
	public RuleName deleteById(Integer id) {
		RuleName result = ruleNameRepository.findById(id).get();
		ruleNameRepository.deleteById(id);
		return result;
	}

}
