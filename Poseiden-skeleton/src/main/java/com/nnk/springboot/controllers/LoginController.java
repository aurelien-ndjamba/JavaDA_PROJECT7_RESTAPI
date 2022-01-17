package com.nnk.springboot.controllers;

import java.security.Principal;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.AuthorityService;
import com.nnk.springboot.services.InfoService;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private InfoService infoService;
	@Autowired
	private AuthorityService authorityService;

	/**
	 * Afficher la page de login
	 * 
	 * @return ModelAndView
	 *  
	 */
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

	/**
	 * Afficher la liste des users de l'application
	 * 
	 * @return ModelAndView
	 *  
	 */
	@GetMapping("/secure/article-details")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher la liste des users de l'application");
		ModelAndView mav = new ModelAndView();
		if (user instanceof UsernamePasswordAuthenticationToken) {
			if (authorityService.getUsernamePasswordLoginAuthority(user).toString().contains("ADMIN"))
				mav.addObject("authority", authorityService.getUsernamePasswordLoginAuthority(user).toString());

			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getUsernamePasswordLoginInfo(user)).toString());
			mav.addObject("authority",
					userInfo.append(authorityService.getUsernamePasswordLoginAuthority(user)).toString());
		} else if (user instanceof OAuth2AuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getOauth2LoginInfo(user)).toString());
		}

		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * Afficher la page d'erreur pour un utilisateur non autorisé
	 * 
	 * @return ModelAndView
	 *  
	 */
	@GetMapping("/error")
	public ModelAndView error(Principal user) {
		logger.info("INFO: Afficher la page d'erreur pour un utilisateur non autorisé");
		ModelAndView mav = new ModelAndView();
		if (user instanceof UsernamePasswordAuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getUsernamePasswordLoginInfo(user)).toString());
		} else if (user instanceof OAuth2AuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getOauth2LoginInfo(user)).toString());
		}

		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}

}
