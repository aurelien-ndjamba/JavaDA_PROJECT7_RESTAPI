package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.ITradeService;

/**
 * Service TradeService
 */
@Service
public class TradeServiceImpl implements ITradeService{
	
	@Autowired
	private TradeRepository tradeRepository;

	public void setTradeRepository(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	@Override
	public List<Trade> findAll() {
		return tradeRepository.findAll();
	}

	@Override
	public @Valid Trade save(@Valid Trade trade) {
		return tradeRepository.save(trade);
	}

	@Override
	public Trade findById(Integer id) {
		return tradeRepository.findById(id).get();
	}

	@Override
	public Trade deleteById(Integer id) {
		Trade result = tradeRepository.findById(id).get();
		tradeRepository.deleteById(id);
		return result;
	}

}
