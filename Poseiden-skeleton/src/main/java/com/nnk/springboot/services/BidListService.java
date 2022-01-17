package com.nnk.springboot.services;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * Service BidListService
 */
@Service
public class BidListService {
	
	@Autowired
	private BidListRepository bidListRepository;

	public void setBidListRepository(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}

	public ArrayList<BidList> findAll() {
		return bidListRepository.findAll();
	}

	public @Valid BidList save(@Valid BidList bid) {
		return bidListRepository.save(bid);
	}

	public BidList findById(Integer id) {
		return bidListRepository.findById(id).get();
	}

	public BidList deleteById(Integer id) {
		BidList result = bidListRepository.findById(id).get();
		bidListRepository.deleteById(id);
		return result;
	}
	
}
