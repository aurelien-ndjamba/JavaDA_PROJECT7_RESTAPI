package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.RuleName;

/**
 * repository "RuleNameRepository"
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

}
