package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Controller
public class UserController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * Afficher les champs pour la création d'un nouvel user
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/user/add")
	public String addUser( Authentication authentication, Model model) {
		logger.info("INFO: Afficher les champs pour la création d'un nouvel user");
		model.addAttribute("user", new User());
		return "user/add";
	}

	/**
	 * Création reussie d'un nouvel user
	 * 
	 * @return String
	 *  
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model, Authentication authentication) {
		if (result.hasErrors()) {
			logger.info("INFO: Erreur existant dans les champs pour la création d'un nouvel user");
			if (result.getFieldError("username") != null)
				model.addAttribute("exUsername", result.getFieldError("username").getDefaultMessage());
			if (result.getFieldError("password") != null)
				model.addAttribute("exPassword", result.getFieldError("password").getDefaultMessage());
			if (result.getFieldError("fullname") != null)
				model.addAttribute("exFullname", result.getFieldError("fullname").getDefaultMessage());
			if (result.getFieldError("role") != null)
				model.addAttribute("exRole", result.getFieldError("role").getDefaultMessage());
			return "user/add";
		} else if (authentication == null) {
			logger.info("INFO: Création reussie d'un nouvel user");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			model.addAttribute("newUserWithNoError",
					"Vous pouvez maintenant vous connecter avec votre username et votre mot de passe.");
			return "/home";
		} else {
			logger.info("INFO: Création reussie d'un nouvel user");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			model.addAttribute("newUserWithNoError", "Nouvel utilisateur ajouté avec succès dans la base de donnée.");
			return "redirect:/secure/article-details";
		}
	}

	/**
	 * Afficher les champs pour la mise à jour d'un user existant
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les champs pour la mise à jour d'un user existant");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	/**
	 * Mise à jour d'un user existant
	 * 
	 * @return String
	 *  
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.info("INFO: Erreur existant dans les champs pour la mise à jour d'un user existant");
			return "user/update";
		}

		logger.info("INFO: Mise à jour reussie d'un user existant");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userRepository.save(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/secure/article-details";
	}

	/**
	 * Suppression d'un user existant
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Suppression reussie d'un user existant");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return "redirect:/secure/article-details";
	}
}
