package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.Trade;

/**
 * repository "TradeRepository"
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
