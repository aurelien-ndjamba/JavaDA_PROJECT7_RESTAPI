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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

@Controller
public class CurveController {
	private Logger logger = Logger.getLogger(this.getClass());

	// TODO: Inject Curve Point service -> OK
	@Autowired
	private CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		logger.info("INFO: Afficher tous les 'curvePoint' de l'application");
		// TODO: find all Curve Point, add to model -> OK
		ArrayList<CurvePoint> curvePoints = curvePointService.findAll();
		model.addAttribute("curvePoints", curvePoints);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid, Model model) {
		logger.info("INFO: Afficher les onglets pour ajouter un nouveau 'curvePoint' dans l'application");
		model.addAttribute("curvePoint", new CurvePoint());
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.info("INFO: Ajouter un nouveau 'curvePoint' dans l'application");
		// TODO: check data valid and save to db, after saving return Curve list -> OK
		if (result.hasErrors()) { 
			if (result.getFieldError("curveId") != null)
				model.addAttribute("exCurveId", result.getFieldError("curveId").getDefaultMessage());
			return "/curvePoint/add";
		} else { 
			curvePointService.save(curvePoint);
			return "redirect:/curvePoint/list";
		}
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("INFO: Afficher les onglets pour mettre à jour un 'curvePoint' déjà existant dans l'application");
		// TODO: get CurvePoint by Id and to model then show to the form -> OK
		CurvePoint curvePoint = curvePointService.findById(id); 
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	} 

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		logger.info("INFO: Mettre à jour un 'curvePoint' déjà existant dans l'application");
		// TODO: check required fields, if valid call service to update Curve and return
		// Curve list -> OK
		if (result.hasErrors()) { 
			if (result.getFieldError("curveId") != null)
				model.addAttribute("exCurveId", result.getFieldError("curveId").getDefaultMessage());
			return "curvePoint/update"; 
		} else { 
			curvePoint.setId(id); 
			curvePointService.save(curvePoint);
			return "redirect:/curvePoint/list";
		}
	} 

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id) {
		logger.info("INFO: Supprimer un 'curvePoint' existant dans l'application");
		// TODO: Find Curve by Id and delete the Curve, return to Curve list -> OK
		curvePointService.deleteById(id);
		return "redirect:/curvePoint/list";
	}
}
