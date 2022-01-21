package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;

/**
 * Service BidListService respectant le contrat dénifi par IBidListService.
 */
@Service
public class BidListServiceImpl implements IBidListService{
	
	@Autowired
	private BidListRepository bidListRepository;

	/**
	 * Setter de BidListRepository. 
	 * 
	 * @param BidListRepository
	 * @return void
	 */
	public void setBidListRepository(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}

	/**
	 * Méthode définie par IBidListService pour lister de tous les objets 'BidList' dans la base de donnée.
	 * 
	 * @return List<BidList>
	 */
	@Override
	public List<BidList> findAll() {
		return bidListRepository.findAll();
	}

	/**
	 * Méthode définie par IBidListService pour ajouter un objet 'BidList' dans la base de donnée.
	 * 
	 * @param BidList
	 * @return @Valid BidList
	 */
	@Override
	public @Valid BidList save(@Valid BidList bid) {
		return bidListRepository.save(bid);
	}

	/**
	 * Méthode définie par IBidListService pour obtenir un objet 'BidList' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return BidList
	 */
	@Override
	public BidList findById(Integer id) {
		return bidListRepository.findById(id).get();
	}

	/**
	 * Méthode définie par IBidListService pour supprimer une objet 'BidList' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return BidList
	 */
	@Override
	public BidList deleteById(Integer id) {
		BidList result = bidListRepository.findById(id).get();
		bidListRepository.deleteById(id);
		return result;
	}
	
}
