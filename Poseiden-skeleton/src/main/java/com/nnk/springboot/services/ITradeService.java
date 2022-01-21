package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.Trade;

public interface ITradeService {
	
	public List<Trade> findAll();

	public @Valid Trade save(@Valid Trade trade);

	public Trade findById(Integer id);

	public Trade deleteById(Integer id);

}
