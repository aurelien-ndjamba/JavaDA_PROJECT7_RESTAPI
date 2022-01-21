package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.IInfoService;

@Controller
public class BidListController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IAuthorityService authorityService;

	// TODO: Inject Bid service -> OK
	@Autowired
	private IBidListService bidListService;

	/**
	 * Afficher tous les 'bidlists' de l'application
	 * 
	 * @return ModelAndView
	 *  
	 */
	@RequestMapping("/bidList/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'bidlists' de l'application");
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
		// TODO: call service find all bids to show to the view -> OK
		List<BidList> bidLists = bidListService.findAll();
		mav.addObject("bidLists", bidLists);
		mav.setViewName("bidList/list");

		return mav;
	}

	/**
	 * Afficher les onglets pour ajouter un nouveau 'bidlist' dans l'application"
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'bidlist' dans l'application");
		model.addAttribute("bidList", new BidList());
		return "bidList/add";
	}

	/**
	 * Ajouter un nouveau 'bidlist' dans l'application
	 * 
	 * @return String
	 *  
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("INFO: Ajouter un nouveau 'bidlist' dans l'application");
		// TODO: check data valid and save to db, after saving return bid list -> OK
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

	/**
	 * Afficher les onglets pour mettre à jour un 'bidlist' déjà existant dans l'application
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'bidlist' déjà existant dans l'application");
		// TODO: get Bid by Id and to model then show to the form -> OK
		BidList bidList = bidListService.findById(id);
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	/**
	 * Mettre à jour un 'bidlist' déjà existant dans l'application
	 * 
	 * @return String
	 *  
	 */
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

	/**
	 * Supprimer un 'bidlist' existant dans l'application
	 * 
	 * @return String
	 *  
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id) {
		logger.info("INFO: Supprimer un 'bidlist' existant dans l'application");
		// TODO: Find Bid by Id and delete the bid, return to Bid list -> OK
		bidListService.deleteById(id);
		return "redirect:/bidList/list";
	}

	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void setInfoService(IInfoService infoService) {
		this.infoService = infoService;
	}

	public void setBidListService(IBidListService bidListService) {
		this.bidListService = bidListService;
	}
}
