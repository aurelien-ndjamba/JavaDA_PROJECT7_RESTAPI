package com.nnk.springboot.controllers;

import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@GetMapping("/")
	public String home(Authentication authentication, Model model) {
		if (authentication != null) {
			return "redirect:/admin/home";
		}
		logger.info("INFO: Afficher la page principale pour un utilisateur non authentifié de l'application");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("INFO: Afficher la page principale pour un utilisateur authentifié de l'application");
		return "redirect:/bidList/list";
	}

}
