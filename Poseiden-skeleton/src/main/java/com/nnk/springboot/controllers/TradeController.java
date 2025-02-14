package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.List;

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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;
import com.nnk.springboot.services.ITradeService;

@Controller
public class TradeController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IAuthorityService authorityService;

	// TODO: Inject Trade service -> OK
	@Autowired
	private ITradeService tradeService;

	/**
	 * Méthode pour afficher tous les 'trades' de l'application
	 * 
	 * @param Principal
	 * @return ModelAndView
	 * 
	 */
	@RequestMapping("/trade/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'trades' de l'application");
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

		// TODO: find all Trade, add to model -> OK
		List<Trade> trades = tradeService.findAll();
		mav.addObject("trades", trades);
		mav.setViewName("trade/list");
		return mav;
	}

	/**
	 * Méthode pour afficher les onglets pour ajouter un nouveau 'trade' dans l'application
	 * 
	 * @param Trade
	 * @param Model
	 * @return String
	 * 
	 */
	@GetMapping("/trade/add")
	public String addTradeForm(Trade trade, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'trade' dans l'application");
		model.addAttribute("trade", new Trade());
		return "trade/add";
	}

	/**
	 * Méthode pour ajouter un nouveau 'trade' dans l'application
	 * 
	 * @param Trade
	 * @return String
	 * 
	 */
	@PostMapping("/trade/validate")
	public String validate(Trade trade) {
		logger.info("INFO: Ajouter un nouveau 'trade' dans l'application");
		// TODO: check data valid and save to db, after saving return Trade list -> OK
		tradeService.save(trade);
		return "redirect:/trade/list";
	}

	/**
	 * Méthode pour afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans
	 * l'application
	 * 
	 * @param Integer
	 * @param Model
	 * @return String
	 * 
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: get Trade by Id and to model then show to the form -> OK
		Trade trade = tradeService.findById(id);
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	/**
	 * Méthode pour mettre à jour un 'trade' déjà existant dans l'application
	 * 
	 * @param integer
	 * @param Trade
	 * @return String
	 * 
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, Trade trade) {
		logger.info("INFO: Mettre à jour un 'trade' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Trade and return
		// Trade list -> OK
		trade.setTradeId(id);
		tradeService.save(trade);
		return "redirect:/trade/list";
	}

	/**
	 * Méthode pour supprimer un 'trade' existant dans l'application
	 * 
	 * @param Integer
	 * @Param Model
	 * @return String
	 * 
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Supprimer un 'trade' existant dans l'application");
		// TODO: Find Trade by Id and delete the Trade, return to Trade list -> OK
		tradeService.deleteById(id);
		return "redirect:/trade/list";
	}

	/**
	 * Setter TradeService
	 * 
	 * @param ITradeService
	 * @return void
	 * 
	 */
	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

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
}
