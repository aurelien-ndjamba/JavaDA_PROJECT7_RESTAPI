package com.nnk.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nnk.springboot.domain.User;

/**
 * repository "UserRepository"
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
	 * Méthode abstraite pour obtenir un utilisateur enregistrés en base de donnée à
	 * partir de son username
	 * 
	 * @param String
	 * @return Optional<User>
	 */
	Optional<User> findByUsername(String username);
}
