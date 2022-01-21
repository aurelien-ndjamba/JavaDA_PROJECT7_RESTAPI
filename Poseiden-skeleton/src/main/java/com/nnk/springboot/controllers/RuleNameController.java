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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;
import com.nnk.springboot.services.IRuleNameService;

@Controller
public class RuleNameController {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private IInfoService infoService;
	@Autowired
	private IAuthorityService authorityService;

	// TODO: Inject RuleName service -> OK
	@Autowired
	private IRuleNameService ruleNameService;

	/**
	 * Méthode pour afficher tous les 'ruleNames' de l'application
	 * 
	 * @param Principal
	 * @return ModelAndView
	 * 
	 */
	@RequestMapping("/ruleName/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'ruleNames' de l'application");
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

		// TODO: find all RuleName, add to model -> OK
		List<RuleName> ruleNames = ruleNameService.findAll();
		mav.addObject("ruleNames", ruleNames);
		mav.setViewName("ruleName/list");
		return mav;
	}

	/**
	 * Méthode pour afficher les onglets pour ajouter un nouveau 'ruleName' dans
	 * l'application
	 * 
	 * @param RuleName
	 * @param Model
	 * @return String
	 * 
	 */
	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName ruleName, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'ruleName' dans l'application");
		model.addAttribute("ruleName", new RuleName());
		return "ruleName/add";
	}

	/**
	 * Méthode pour ajouter un nouveau 'ruleName' dans l'application
	 * 
	 * @param RuleName
	 * @return String
	 * 
	 */
	@PostMapping("/ruleName/validate")
	public String validate(RuleName ruleName) {
		logger.info("INFO: Ajouter un nouveau 'ruleName' dans l'application");
		// TODO: check data valid and save to db, after saving return RuleName list ->
		// OK
		ruleNameService.save(ruleName);
		return "redirect:/ruleName/list";
	}

	/**
	 * Méthode pour afficher les onglets pour mettre à jour un 'ruleName' déjà
	 * existant dans l'application
	 * 
	 * @param Integer
	 * @param Model
	 * @return String
	 * 
	 */
	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: get RuleName by Id and to model then show to the form -> OK
		RuleName ruleName = ruleNameService.findById(id);
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	/**
	 * Méthode pour mettre à jour un 'ruleName' déjà existant dans l'application
	 * 
	 * @param Integer
	 * @param RuleName
	 * @return String
	 * 
	 */
	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, RuleName ruleName) {
		logger.info("INFO: Mettre à jour un 'ruleName' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update RuleName and
		// return RuleName list -> OK
		ruleName.setId(id);
		ruleNameService.save(ruleName);
		return "redirect:/ruleName/list";
	}

	/**
	 * Méthode pour supprimer un 'ruleName' existant dans l'application
	 * 
	 * @param Integer
	 * @param Model
	 * @return String
	 * 
	 */
	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Supprimer un 'ruleName' existant dans l'application");
		// TODO: Find RuleName by Id and delete the RuleName, return to Rule list -> OK
		ruleNameService.deleteById(id);
		return "redirect:/ruleName/list";
	}

	/**
	 * Setter RuleNameService
	 * 
	 * @param IRuleNameService
	 * @return void
	 * 
	 */
	public void setRuleNameService(IRuleNameService ruleNameService) {
		this.ruleNameService = ruleNameService;
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
	 * Setter AuthorityService
	 * 
	 * @param IAuthorityService
	 * @return void
	 * 
	 */
	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}
}
