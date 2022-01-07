package com.nnk.springboot.controllers;

import java.util.ArrayList;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@Controller
public class TradeController {
	private Logger logger = Logger.getLogger(this.getClass());

	// TODO: Inject Trade service -> OK
	@Autowired
	private TradeService tradeService;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		logger.info("INFO: Afficher tous les 'trade' de l'application");
		// TODO: find all Trade, add to model -> OK
		ArrayList<Trade> trades = tradeService.findAll();
		model.addAttribute("trades", trades);
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'trade' dans l'application");
		model.addAttribute("trade", new Trade());
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(Trade trade) {
		logger.info("INFO: Ajouter un nouveau 'trade' dans l'application");
		// TODO: check data valid and save to db, after saving return Trade list -> OK
		tradeService.save(trade);
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: get Trade by Id and to model then show to the form -> OK
		Trade trade = tradeService.findById(id);
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, Trade trade) {
		logger.info("INFO: Mettre à jour un 'trade' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Trade and return
		// Trade list -> OK
		trade.setTradeId(id);
		tradeService.save(trade);
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Supprimer un 'trade' existant dans l'application");
		// TODO: Find Trade by Id and delete the Trade, return to Trade list -> OK
		tradeService.deleteById(id);
		return "redirect:/trade/list";
	}
}
