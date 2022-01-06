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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

@Controller
public class RuleNameController {
	private Logger logger = Logger.getLogger(this.getClass());
	
    // TODO: Inject RuleName service -> OK
	@Autowired
	private RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
    	logger.info("INFO: Afficher tous les 'ruleName' de l'application");
        // TODO: find all RuleName, add to model -> OK
    	ArrayList<RuleName> ruleNames = ruleNameService.findAll();
		model.addAttribute("ruleNames", ruleNames);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid, Model model) {
    	logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'ruleName' dans l'application");
    	model.addAttribute("ruleName",new RuleName());
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	logger.info("INFO: Ajouter un nouveau 'ruleName' dans l'application");
        // TODO: check data valid and save to db, after saving return RuleName list -> OK
    	if (result.hasErrors()) {
			return "/ruleName/add";
		} else {
			ruleNameService.save(ruleName);
			return "redirect:/ruleName/list"; 
		}
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("INFO: Afficher les onglets pour mettre à jour un 'ruleName' déjà existant dans l'application");
        // TODO: get RuleName by Id and to model then show to the form -> OK
    	Rating ruleName = ruleNameService.findById(id);
		model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	logger.info("INFO: Mettre à jour un 'ruleName' déjà existant dans l'application");
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list -> OK
    	if (result.hasErrors()) {
			return "ruleName/update";
		} else {
			ruleNameService.save(ruleName);
			return "redirect:/ruleName/list";
		}
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	logger.info("INFO: Supprimer un 'ruleName' existant dans l'application");
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list -> OK
    	ruleNameService.deleteById(id);
        return "redirect:/ruleName/list";
    }
}
