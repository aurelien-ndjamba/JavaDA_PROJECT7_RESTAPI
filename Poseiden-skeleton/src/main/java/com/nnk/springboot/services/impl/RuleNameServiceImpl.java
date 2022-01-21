package com.nnk.springboot.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.IRuleNameService;

/**
 * Service RuleNameService
 */
@Service
public class RuleNameServiceImpl implements IRuleNameService{
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

	public void setRuleNameRepository(RuleNameRepository ruleNameRepository) {
		this.ruleNameRepository = ruleNameRepository;
	}

	@Override
	public List<RuleName> findAll() {
		return ruleNameRepository.findAll();
	}

	@Override
	public RuleName save(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	@Override
	public RuleName findById(Integer id) {
		return ruleNameRepository.findById(id).get();
	}

	@Override
	public RuleName deleteById(Integer id) {
		RuleName result = ruleNameRepository.findById(id).get();
		ruleNameRepository.deleteById(id);
		return result;
	}

}
