package com.nnk.springboot.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

@Controller
public class RatingController {
	private Logger logger = Logger.getLogger(this.getClass());
	
	// TODO: Inject Rating service
	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		logger.info("INFO: Afficher tous les 'rating' de l'application");
		// TODO: find all Rating, add to model -> OK
		ArrayList<Rating> ratings = ratingService.findAll();
		model.addAttribute("ratings", ratings);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'rating' dans l'application");
		model.addAttribute("rating",new Rating());
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result) {
		logger.info("INFO: Ajouter un nouveau 'rating' dans l'application");
		// TODO: check data valid and save to db, after saving return Rating list -> OK
		if (result.hasErrors()) {
			return "/rating/add";
		} else {
			ratingService.save(rating);
			return "redirect:/curvePoint/list"; 
		}
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'rating' déjà existant dans l'application");
		// TODO: get Rating by Id and to model then show to the form -> OK
		Rating rating = ratingService.findById(id);
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		logger.info("INFO: Mettre à jour un 'rating' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Rating and
		// return Rating list -> OK
		if (result.hasErrors()) {
			return "rating/update";
		} else {
			ratingService.save(rating);
			return "redirect:/rating/list";
		}
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id) {
		// TODO: Find Rating by Id and delete the Rating, return to Rating list -> OK
		ratingService.deleteById(id);
		return "redirect:/rating/list";
	}
}
