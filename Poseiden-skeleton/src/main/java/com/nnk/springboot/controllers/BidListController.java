package com.nnk.springboot.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

import javax.sound.midi.MidiDevice.Info;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
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
import com.nnk.springboot.services.AuthorityService;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.services.InfoService;

@Controller
public class BidListController extends InfoService {

	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private InfoService infoService;
	@Autowired
	private AuthorityService authorityService;

	// TODO: Inject Bid service -> OK
	@Autowired
	private BidListService bidListService;

//	private OAuth2AuthorizedClientService authorizedClientService;
//	public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
//				this.authorizedClientService = authorizedClientService;
//			}

//		public String getUserInfo(Principal user) {
//		StringBuffer userInfo = new StringBuffer();
//		if (user instanceof UsernamePasswordAuthenticationToken) {
//			userInfo.append(getUsernamePasswordLoginInfo(user));
//		} else if (user instanceof OAuth2AuthenticationToken) {
//			userInfo.append(getOauth2LoginInfo(user));
//		}
//		return userInfo.toString();
//	}

//	private StringBuffer getOauth2LoginInfo(Principal user) {
//		StringBuffer loginInfo = new StringBuffer();
//		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
////		OAuth2AuthorizedClient authClient = this.authorizedClientService
////				.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
//		if (authToken.isAuthenticated()) {
//			Map<String, Object> userAttributes = (authToken.getPrincipal()).getAttributes(); //(DefaultOAuth2User) 
////			String userToken = authClient.getAccessToken().getTokenValue();
//			loginInfo.append(userAttributes.get("login"));
//		} else {
//			loginInfo.append("NA");
//		}
//		return loginInfo;
//	}
//
//	private StringBuffer getUsernamePasswordLoginInfo(Principal user) {
//		StringBuffer usernameInfo = new StringBuffer();
//		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
//		if (token.isAuthenticated()) {
//			User u = (User) token.getPrincipal();
//			usernameInfo.append(u.getUsername());
//		} else {
//			usernameInfo.append("NA");
//		}
//		return usernameInfo;
//	}

	@RequestMapping("/bidList/list")
	public ModelAndView home(Principal user) {
		logger.info("INFO: Afficher tous les 'bidlists' de l'application");
		// TODO: call service find all bids to show to the view -> OK
		ModelAndView mav = new ModelAndView();
		ArrayList<BidList> bidLists = bidListService.findAll();
		mav.addObject("bidLists", bidLists);

		if (user instanceof UsernamePasswordAuthenticationToken) {
			if (authorityService.getUsernamePasswordLoginAuthority(user).toString().contains("ADMIN"))
				mav.addObject("authority", authorityService.getUsernamePasswordLoginAuthority(user).toString());
			
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getUsernamePasswordLoginInfo(user)).toString());
			

//			mav.addObject("userInfo", user);
//			mav.addObject("userInfo", ((UsernamePasswordAuthenticationToken) user).getPrincipal());
//			mav.addObject("userInfo", ((UsernamePasswordAuthenticationToken) user).getAuthorities());

//			mav.addObject("userInfo", getUsernamePasswordLoginInfo(user)); 
//			mav.addObject("un", user.getName()); // Interface
//			mav.addObject("us", user.toString()); // Interface
//			mav.addObject("up", ((UsernamePasswordAuthenticationToken) user).getPrincipal());
//			mav.addObject("uc", ((UsernamePasswordAuthenticationToken) user).getCredentials());
//			mav.addObject("ua", ((UsernamePasswordAuthenticationToken) user).isAuthenticated());
//			mav.addObject("uo", ((UsernamePasswordAuthenticationToken) user).getAuthorities());
//			mav.addObject("ud", ((UsernamePasswordAuthenticationToken) user).getDetails());
		} else if (user instanceof OAuth2AuthenticationToken) {
			StringBuffer userInfo = new StringBuffer();
			mav.addObject("userInfo", userInfo.append(infoService.getOauth2LoginInfo(user)).toString());

//			mav.addObject("userInfo", user ); 
//			mav.addObject("userInfo", ((OAuth2AuthenticationToken) user).getPrincipal());
//			mav.addObject("userInfo", ((OAuth2AuthenticationToken) user).getAuthorities() ); 
		}

		mav.setViewName("bidList/list");
		return mav;
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
	public String deleteBid(@PathVariable("id") Integer id) {
		logger.info("INFO: Supprimer un 'bidlist' existant dans l'application");
		// TODO: Find Bid by Id and delete the bid, return to Bid list -> OK
		bidListService.deleteById(id);
		return "redirect:/bidList/list";
	}
}
