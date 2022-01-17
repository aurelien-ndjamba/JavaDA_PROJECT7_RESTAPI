package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.ArrayList;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.AuthorityService;
import com.nnk.springboot.services.InfoService;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private InfoService infoService;
	@Autowired
	private AuthorityService authorityService;

	// TODO: Inject Rating service
	@Autowired
	private RatingService ratingService;

	/**
	 * Afficher tous les 'ratings' de l'application
	 * 
	 * @return ModelAndView
	 *  
	 */
	@RequestMapping("/rating/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'ratings' de l'application");
		ModelAndView mav = new ModelAndView();

		if (user instanceof UsernamePasswordAuthenticationToken) {
			if (authorityService.getUsernamePasswordLoginAuthority(user).toString().contains("ADMIN"))
				mav.addObject("authority", authorityService.getUsernamePasswordLoginAuthority(user).toString());

			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getUsernamePasswordLoginInfo(user)).toString());
		} else if (user instanceof OAuth2AuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getOauth2LoginInfo(user)).toString());
		}

		// TODO: find all Rating, add to model -> OK
		ArrayList<Rating> ratings = ratingService.findAll();
		mav.addObject("ratings", ratings);
		mav.setViewName("rating/list");
		return mav;
	}

	/**
	 * Afficher les onglets pour ajouter un nouveau 'rating' dans l'application
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'rating' dans l'application");
		model.addAttribute("rating", new Rating());
		return "rating/add";
	}

	/**
	 * Ajouter un nouveau 'rating' dans l'application
	 * 
	 * @return String
	 *  
	 */
	@PostMapping("/rating/validate")
	public String validate(Rating rating) {
		logger.info("INFO: Ajouter un nouveau 'rating' dans l'application");
		// TODO: check data valid and save to db, after saving return Rating list -> OK
		ratingService.save(rating);
		return "redirect:/rating/list";
	}

	/**
	 * Afficher les onglets pour mettre à jour un 'rating' déjà existant dans l'application
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'rating' déjà existant dans l'application");
		// TODO: get Rating by Id and to model then show to the form -> OK
		Rating rating = ratingService.findById(id);
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	/**
	 * Mettre à jour un 'rating' déjà existant dans l'application
	 * 
	 * @return String
	 *  
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, Rating rating) {
		logger.info("INFO: Mettre à jour un 'rating' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Rating and
		// return Rating list -> OK
		rating.setId(id);
		ratingService.save(rating);
		return "redirect:/rating/list";
	}

	/**
	 * Supprimer un 'rating' existant dans l'application
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id) {
		logger.info("INFO: Supprimer un 'rating' existant dans l'application");
		// TODO: Find Rating by Id and delete the Rating, return to Rating list -> OK
		ratingService.deleteById(id);
		return "redirect:/rating/list";
	}
}
