package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.RuleName;

public interface IRuleNameService {

	public List<RuleName> findAll();

	public @Valid RuleName save(@Valid RuleName ruleName);

	public RuleName findById(Integer id);

	public RuleName deleteById(Integer id);

}
