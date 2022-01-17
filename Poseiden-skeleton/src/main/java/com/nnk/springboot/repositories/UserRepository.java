package com.nnk.springboot.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;
 
/**
 * repository "UserRepository"
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //, JpaSpecificationExecutor<User> 
	ArrayList<User> findAll();
	Optional<User> findByUsername(String username);
}
