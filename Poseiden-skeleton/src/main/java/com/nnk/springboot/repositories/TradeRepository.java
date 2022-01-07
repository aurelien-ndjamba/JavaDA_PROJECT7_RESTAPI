package com.nnk.springboot.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
	ArrayList<Trade> findAll();
}
