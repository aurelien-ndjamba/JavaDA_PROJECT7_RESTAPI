package com.nnk.springboot.services.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.IUserService;

/**
 * Service UserService respectant le contrat dénifi par IUserService.
 */
@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Setter de TradeRepository. 
	 * 
	 * @param UserRepository
	 * @return void
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Méthode définie par IUserService pour lister de tous les objets 'User' dans la base de donnée.
	 * 
	 * @return List<User>
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Méthode définie par IUserService pour ajouter un objet 'User' dans la base de donnée.
	 * 
	 * @param User
	 * @return @Valid User
	 */
	@Override
	public @Valid User save(@Valid User user) {
		return userRepository.save(user);
	}

	/**
	 * Méthode définie par IUserService pour obtenir un objet 'User' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return User
	 */
	@Override
	public User findById(Integer id) {
		return userRepository.findById(id).get();
	}
	
	/**
	 * Méthode définie par IUserService pour obtenir une objet 'User' à partir de son attribut 'username' dans la base de donnée.
	 * 
	 * @param String
	 * @return Optional<User> 
	 */
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Méthode définie par IUserService pour supprimer une objet 'User' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return User
	 */
	@Override
	public User deleteById(Integer id) {
		User result = userRepository.findById(id).get();
		userRepository.deleteById(id);
		return result;
	}

	/**
	 * Méthode définie par IUserService pour supprimer une objet 'User' en parametre dans la base de donnée.
	 * 
	 * @param User
	 * @return User
	 */
	@Override
	public User delete(User user) {
		User result = new User();
		if (userRepository.existsById(user.getId())){
			result = userRepository.findById(user.getId()).get();
			userRepository.deleteById(user.getId());
		}
		return result;
	}

}
