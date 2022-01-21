package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.ITradeService;

/**
 * Service TradeService respectant le contrat dénifi par ITradeService.
 */
@Service
public class TradeServiceImpl implements ITradeService{
	
	@Autowired
	private TradeRepository tradeRepository;

	/**
	 * Setter de TradeRepository. 
	 * 
	 * @param TradeRepository
	 * @return void
	 */
	public void setTradeRepository(TradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}

	/**
	 * Méthode définie par ITradeService pour lister de tous les objets 'Trade' dans la base de donnée.
	 * 
	 * @return List<Trade>
	 */
	@Override
	public List<Trade> findAll() {
		return tradeRepository.findAll();
	}

	/**
	 * Méthode définie par ITradeService pour ajouter un objet 'Trade' dans la base de donnée.
	 * 
	 * @param Trade
	 * @return @Valid Trade
	 */
	@Override
	public @Valid Trade save(@Valid Trade trade) {
		return tradeRepository.save(trade);
	}

	/**
	 * Méthode définie par ITradeService pour obtenir un objet 'Trade' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return Trade
	 */
	@Override
	public Trade findById(Integer id) {
		return tradeRepository.findById(id).get();
	}

	/**
	 * Méthode définie par ITradeService pour supprimer une objet 'Trade' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return Trade
	 */
	@Override
	public Trade deleteById(Integer id) {
		Trade result = tradeRepository.findById(id).get();
		tradeRepository.deleteById(id);
		return result;
	}

}
