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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.AuthorityService;
import com.nnk.springboot.services.InfoService;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController extends InfoService{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private InfoService infoService;
	@Autowired
	private AuthorityService authorityService;

	// TODO: Inject RuleName service -> OK
	@Autowired
	private RuleNameService ruleNameService;
	
	@RequestMapping("/ruleName/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'ruleNames' de l'application");
		ModelAndView mav = new ModelAndView(); 
		
		if (user instanceof UsernamePasswordAuthenticationToken) {
			if (authorityService.getUsernamePasswordLoginAuthority(user).toString().contains("ADMIN"))
				mav.addObject("authority", authorityService.getUsernamePasswordLoginAuthority(user).toString());
			
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(getUsernamePasswordLoginInfo(user)).toString());
		} else if (user instanceof OAuth2AuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(getOauth2LoginInfo(user)).toString());}
		
		// TODO: find all RuleName, add to model -> OK
		ArrayList<RuleName> ruleNames = ruleNameService.findAll();
		mav.addObject("ruleNames", ruleNames);
		mav.setViewName("ruleName/list");
		return mav; //"ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'ruleName' dans l'application");
		model.addAttribute("ruleName", new RuleName());
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(RuleName ruleName) {
		logger.info("INFO: Ajouter un nouveau 'ruleName' dans l'application");
		// TODO: check data valid and save to db, after saving return RuleName list ->
		// OK
		ruleNameService.save(ruleName);
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: get RuleName by Id and to model then show to the form -> OK
		RuleName ruleName = ruleNameService.findById(id);
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, RuleName ruleName) {
		logger.info("INFO: Mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update RuleName and
		// return RuleName list -> OK
		ruleName.setId(id);
		ruleNameService.save(ruleName);
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Supprimer un 'ruleName' existant dans l'application");
		// TODO: Find RuleName by Id and delete the RuleName, return to Rule list -> OK
		ruleNameService.deleteById(id);
		return "redirect:/ruleName/list";
	}
}
