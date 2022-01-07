package com.nnk.springboot.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	
	@Autowired
	private RuleNameRepository ruleNameRepository;

	public ArrayList<RuleName> findAll() {
		return ruleNameRepository.findAll();
	}

	public RuleName save(RuleName ruleName) {
		return ruleNameRepository.save(ruleName);
	}

	public RuleName findById(Integer id) {
		return ruleNameRepository.findById(id).get();
	}

	public void deleteById(Integer id) {
		ruleNameRepository.deleteById(id);
	}

}
