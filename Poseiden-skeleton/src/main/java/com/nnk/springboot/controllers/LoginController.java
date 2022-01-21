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
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private UserRepository userService;
	@Autowired
	private IInfoService infoService;
	@Autowired
	private IAuthorityService authorityService;

	/**
	 * Setter AuthorityService
	 * 
	 * @param IAuthorityService
	 * @return void
	 * 
	 */
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	/**
	 * Setter InfoService
	 * 
	 * @param IInfoService
	 * @return void
	 * 
	 */
	public void setInfoService(IInfoService infoService) {
		this.infoService = infoService;
	}

	/**
	 * Méthode pour afficher la page de login
	 * 
	 * @param Authentication
	 * @return ModelAndView
	 *  
	 */
	@GetMapping("/login")
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
	 * Méthode pour afficher la liste des users de l'application
	 * 
	 * @param Principal
	 * @return ModelAndView
	 *  
	 */
	@GetMapping("/secure/article-details")
	public ModelAndView usersList(Principal user) {
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

		mav.addObject("users", userService.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	/**
	 * Méthode pour afficher la page d'erreur pour un utilisateur non autorisé
	 * 
	 * @param Principal
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
