package com.nnk.springboot.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {
	
	@Autowired
	private BidListRepository bidListRepository;

	public ArrayList<BidList> findAll() {
		return bidListRepository.findAll();
	}

	public BidList save(@Valid BidList bid) {
		bidListRepository.save(bid);
		return bid;
	}

}
