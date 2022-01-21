package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.BidList;

public interface IBidListService {

	public List<BidList> findAll();

	public @Valid BidList save(@Valid BidList bid);

	public BidList findById(Integer id);

	public BidList deleteById(Integer id);

}
