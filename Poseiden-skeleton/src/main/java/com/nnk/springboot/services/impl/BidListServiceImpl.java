package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;

/**
 * Service BidListService
 */
@Service
public class BidListServiceImpl implements IBidListService{
	
	@Autowired
	private BidListRepository bidListRepository;

	public void setBidListRepository(BidListRepository bidListRepository) {
		this.bidListRepository = bidListRepository;
	}

	@Override
	public List<BidList> findAll() {
		return bidListRepository.findAll();
	}

	@Override
	public @Valid BidList save(@Valid BidList bid) {
		return bidListRepository.save(bid);
	}

	@Override
	public BidList findById(Integer id) {
		return bidListRepository.findById(id).get();
	}

	@Override
	public BidList deleteById(Integer id) {
		BidList result = bidListRepository.findById(id).get();
		bidListRepository.deleteById(id);
		return result;
	}
	
}
