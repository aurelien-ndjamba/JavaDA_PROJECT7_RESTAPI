package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {
	ArrayList<BidList> findAll();
}
