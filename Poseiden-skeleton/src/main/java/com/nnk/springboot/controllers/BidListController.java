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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

@Controller
public class BidListController {
	private Logger logger = Logger.getLogger(this.getClass());

	// TODO: Inject Bid service -> OK
	@Autowired
	private BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		logger.info("INFO: Afficher tous les 'bidlist' de l'application");
		// TODO: call service find all bids to show to the view -> OK
		ArrayList<BidList> bidLists = bidListService.findAll();
		model.addAttribute("bidLists", bidLists);
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'bidlist' dans l'application");
		model.addAttribute("bidList", new BidList());
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("INFO: Ajouter un nouveau 'bidlist' dans l'application");
		// TODO: check data valid and save to db, after saving return bid list -> OK
//		System.out.println("===================================");
//		System.out.println(result.getFieldErrorCount());
//		System.out.println("===================================");
//		System.out.println(result.getTarget());
//		System.out.println("===================================");
//		System.out.println(result.getFieldError().getDefaultMessage());
//		System.out.println("===================================");
//		System.out.println(result.getFieldError("type").getDefaultMessage()); 
//		System.out.println("===================================");
		if (result.hasErrors()) { 
			if (result.getFieldError("account") != null)
				model.addAttribute("exAccount", result.getFieldError("account").getDefaultMessage());
			if (result.getFieldError("type") != null)
				model.addAttribute("exType", result.getFieldError("type").getDefaultMessage());
			return "bidList/add";
		} else {
			bidListService.save(bid);
			return "redirect:/bidList/list";
		}
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'bidlist' déjà existant dans l'application");
		// TODO: get Bid by Id and to model then show to the form -> OK
		BidList bidList = bidListService.findById(id); 
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	} 

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		logger.info("INFO: Mettre à jour un 'bidlist' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Bid and return
		// list Bid -> OK 
		if (result.hasErrors()) {
			if (result.getFieldError("account") != null)
				model.addAttribute("exAccount", result.getFieldError("account").getDefaultMessage());
			if (result.getFieldError("type") != null)
				model.addAttribute("exType", result.getFieldError("type").getDefaultMessage());
			return "bidList/update"; 
		} else { 
			bidList.setBidListId(id);
			bidListService.save(bidList);
			return "redirect:/bidList/list";
		}
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id) { // , Model model
		logger.info("INFO: Supprimer un 'bidlist' existant dans l'application");
		// TODO: Find Bid by Id and delete the bid, return to Bid list -> OK
		bidListService.deleteById(id);
		return "redirect:/bidList/list";
	}
}
