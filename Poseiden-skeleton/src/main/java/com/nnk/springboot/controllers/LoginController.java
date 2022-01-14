package com.nnk.springboot.controllers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	
//	private final OAuth2AuthorizedClientService authorizedClientService;
//	
//	public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
//		this.authorizedClientService = authorizedClientService;
//	}

	@GetMapping("login")
	public ModelAndView login(Authentication authentication) {
		logger.info("INFO: Afficher la page de login");
		ModelAndView mav = new ModelAndView();
		if (authentication != null) {
			mav.addObject("error", "Vous êtes déjà authentifié dans l'application.");
			mav.setViewName("bidList/list");
		} else
			mav.setViewName("login");

		return mav;
	}
	
//	@RolesAllowed("ADMIN")
	@GetMapping("secure/article-details")
	public ModelAndView getAllUserArticles() {
		logger.info("INFO: Afficher la liste des users de l'application");
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

//	@GetMapping("error")
//	public ModelAndView error() {
//		logger.info("INFO: Afficher la page d'erreur pour un utilisateur non autorisé");
//		ModelAndView mav = new ModelAndView();
//		String errorMessage = "You are not authorized for the requested data.";
//		mav.addObject("errorMsg", errorMessage);
//		mav.setViewName("403");
//		return mav;
//	}

}
