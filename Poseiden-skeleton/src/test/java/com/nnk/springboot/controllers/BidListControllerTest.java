package com.nnk.springboot.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IBidListService;
import com.nnk.springboot.services.IInfoService;

@SpringBootTest
public class BidListControllerTest {

	@Autowired
	private BidListController bidListController;
	@Mock
	private IBidListService bidListServiceMock;
	@Mock
	private IAuthorityService authorityServiceMock;
	@Mock
	private IInfoService infoServiceMock;

	@Test
	public void testHomeWhenUsernamePasswordAuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		BidList bidList = new BidList();
		ArrayList<BidList> bidLists = new ArrayList<BidList>();
		bidLists.add(bidList);

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(
				"username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(bidListServiceMock.findAll()).thenReturn(bidLists);
		bidListController.setAuthorityService(authorityServiceMock);
		bidListController.setBidListService(bidListServiceMock);

		// THEN
		assertThat(bidListController.home(user).getViewName()).isEqualTo("bidList/list");
		assertThat(bidListController.home(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(bidListController.home(user).getModel().containsKey("bidLists")).isEqualTo(true);
		assertThat(bidListController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);
	}

	@Test
	public void testHomeWhenOAuth2AuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		BidList bidList = new BidList();
		ArrayList<BidList> bidLists = new ArrayList<BidList>();
		bidLists.add(bidList);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");

		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		// WHEN
		when(infoServiceMock.getOauth2LoginInfo(user)).thenReturn(authority);
		when(bidListServiceMock.findAll()).thenReturn(bidLists);
		bidListController.setInfoService(infoServiceMock);
		bidListController.setBidListService(bidListServiceMock);

		// THEN
		assertThat(bidListController.home(user).getViewName()).isEqualTo("bidList/list");
		assertThat(bidListController.home(user).getModel().containsKey("authority")).isEqualTo(false);
		assertThat(bidListController.home(user).getModel().containsKey("bidLists")).isEqualTo(true);
		assertThat(bidListController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);

	}

	@Test
	public void testAddBidForm() throws Exception {
		// GIVEN
		BidList bid = new BidList();
		bid.setAccount("a");
		bid.setAsk(12.0);
		bid.setAskQuantity(12.0);
		bid.setBenchmark("a");
		bid.setBid(12.0);
		bid.setBidListDate(null);
		bid.setBidListId(1);
		bid.setBidQuantity(12.0);
		bid.setBook("a");
		bid.setCommentary("a");
		bid.setCreationDate(null);
		bid.setCreationName("a");
		bid.setDealName("a");
		bid.setRevisionDate(null);
		bid.setSecurity("a");
		bid.setSide("a");
		bid.setSourceListId("a");
		bid.setStatus("a");
		bid.setTrader("a");
		bid.setType("a");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(bidListController.addBidForm(bid, model)).isEqualTo("bidList/add");
	}

	@Test
	public void testValidateWhenFieldErrorIsAccount() throws Exception {
		// GIVEN
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		result.rejectValue("account", "defaultMessage1");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(bidListController.validate(bidList, result, model)).isEqualTo("bidList/add");
	}

	@Test
	public void testValidateWhenFieldErrorIsType() throws Exception {
		// GIVEN
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		result.rejectValue("type", "defaultMessage1");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(bidListController.validate(bidList, result, model)).isEqualTo("bidList/add");
	}

	@Test
	public void testValidatedWhenNoError() throws Exception {
		// GIVEN
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		Model model = new ExtendedModelMap();
		// WHEN
		when(bidListServiceMock.save(bidList)).thenReturn(new BidList());
		bidListController.setBidListService(bidListServiceMock);
		// THEN
		assertThat(bidListController.validate(bidList, result, model)).isEqualTo("redirect:/bidList/list");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(bidListServiceMock.findById(id)).thenReturn(new BidList());
		bidListController.setBidListService(bidListServiceMock);
		// THEN
		assertThat(bidListController.showUpdateForm(id, model)).isEqualTo("bidList/update");
	}

	@Test
	public void testUpdateBidWhenFieldErrorIsAccount() throws Exception {
		// GIVEN
		int id = 8;
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		result.rejectValue("account", "defaultMessage1");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(bidListController.updateBid(id, bidList, result, model)).isEqualTo("bidList/update");
	}

	@Test
	public void testUpdateBidWhenFieldErrorIsType() throws Exception {
		// GIVEN
		int id = 8;
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		result.rejectValue("type", "defaultMessage1");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(bidListController.updateBid(id, bidList, result, model)).isEqualTo("bidList/update");
	}

	@Test
	public void testUpdateBidWhenNoError() throws Exception {
		// GIVEN
		int id = 8;
		BidList bidList = new BidList();
		Map<String, String> target = new HashMap<String, String>();
		BindingResult result = new MapBindingResult(target, "name");
		Model model = new ExtendedModelMap();
		// WHEN
		when(bidListServiceMock.save(bidList)).thenReturn(new BidList());
		bidListController.setBidListService(bidListServiceMock);
		// THEN
		assertThat(bidListController.updateBid(id, bidList, result, model)).isEqualTo("redirect:/bidList/list");
	}

	@Test
	public void testDeleteBid() throws Exception {
		// GIVEN
		int id = 8;
		// WHEN
		when(bidListServiceMock.deleteById(id)).thenReturn(new BidList());
		bidListController.setBidListService(bidListServiceMock);
		// THEN
		assertThat(bidListController.deleteBid(id)).isEqualTo("redirect:/bidList/list");
	}

}
