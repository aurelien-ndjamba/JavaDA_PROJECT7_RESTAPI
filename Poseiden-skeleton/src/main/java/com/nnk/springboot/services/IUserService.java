package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.nnk.springboot.domain.User;

/**
 * Interface IUserService
 */
public interface IUserService {

	/**
	 * Méthode pour lister tous les objets 'User' dans la base de donnée
	 * 
	 * @return List<User>
	 */
	public List<User> findAll();

	/**
	 * Méthode pour ajouter un objet 'User' dans la base de donnée
	 * 
	 * @param User
	 * @return @Valid User
	 */
	public @Valid User save(@Valid User user);

	/**
	 * Méthode pour obtenir un objet 'User' à partir de sa clé primaire 'id' dans
	 * la base de donnée
	 * 
	 * @param Integer
	 * @return User
	 */
	public User findById(Integer id);

	/**
	 * Méthode pour obtenir un objet 'User' à partir de son username dans la base
	 * de donnée
	 * 
	 * @param String
	 * @return User
	 */
	public Optional<User> findByUsername(String username);

	/**
	 * Méthode pour supprimer un objet 'User' à partir de sa clé primaire 'id' dans
	 * la base de donnée
	 * 
	 * @param Integer
	 * @return User
	 */
	public User deleteById(Integer id);

	/**
	 * Méthode pour supprimer un objet 'User' dans la base de donnée
	 * 
	 * @param User
	 * @return User
	 */
	public User delete(User user);

}
