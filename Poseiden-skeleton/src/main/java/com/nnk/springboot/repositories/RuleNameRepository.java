package com.nnk.springboot.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.RuleName;

@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {
	ArrayList<RuleName> findAll();
}
