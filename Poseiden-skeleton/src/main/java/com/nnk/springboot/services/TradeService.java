package com.nnk.springboot.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * Service TradeService
 */
@Service
public class TradeService {
	
	@Autowired
	private TradeRepository tradeRepository;

	public void setTradeRepository(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	public ArrayList<Trade> findAll() {
		return tradeRepository.findAll();
	}

	public @Valid Trade save(@Valid Trade trade) {
		return tradeRepository.save(trade);
	}

	public Trade findById(Integer id) {
		return tradeRepository.findById(id).get();
	}

	public Trade deleteById(Integer id) {
		Trade result = tradeRepository.findById(id).get();
		tradeRepository.deleteById(id);
		return result;
	}

}
