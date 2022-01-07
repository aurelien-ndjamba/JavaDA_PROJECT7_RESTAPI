package com.nnk.springboot.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{ //, JpaSpecificationExecutor<User> 
	ArrayList<User> findAll();
}
