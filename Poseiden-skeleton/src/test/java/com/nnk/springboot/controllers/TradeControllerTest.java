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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;
import com.nnk.springboot.services.ITradeService;

@SpringBootTest
public class TradeControllerTest {
	
	@Autowired
	private TradeController tradeController;
	@Mock
	private ITradeService tradeServiceMock;
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

		Trade trade = new Trade();
		ArrayList<Trade> trades = new ArrayList<Trade>();
		trades.add(trade);

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(
				"username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(tradeServiceMock.findAll()).thenReturn(trades);
		tradeController.setAuthorityService(authorityServiceMock);
		tradeController.setTradeService(tradeServiceMock);

		// THEN
		assertThat(tradeController.home(user).getViewName()).isEqualTo("trade/list");
		assertThat(tradeController.home(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(tradeController.home(user).getModel().containsKey("trades")).isEqualTo(true);
		assertThat(tradeController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);
	}

	@Test
	public void testHomeWhenOAuth2AuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		Trade trade = new Trade();
		ArrayList<Trade> trades = new ArrayList<Trade>();
		trades.add(trade);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");
		// WHEN
		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		// WHEN
		when(infoServiceMock.getOauth2LoginInfo(user)).thenReturn(authority);
		when(tradeServiceMock.findAll()).thenReturn(trades);
		tradeController.setInfoService(infoServiceMock);
		tradeController.setTradeService(tradeServiceMock);

		// THEN
		assertThat(tradeController.home(user).getViewName()).isEqualTo("trade/list");
		assertThat(tradeController.home(user).getModel().containsKey("authority")).isEqualTo(false);
		assertThat(tradeController.home(user).getModel().containsKey("trades")).isEqualTo(true);
		assertThat(tradeController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);

	}
	
	@Test
	public void testAddTradeForm() throws Exception {
		// GIVEN
		Trade trade = new Trade();
		trade.setAccount("a");
		trade.setBenchmark("a");
		trade.setBook("a");
		trade.setBuyPrice(1.);
		trade.setBuyQuantity(1.);
		trade.setCreationDate(null);
		trade.setCreationName("a");
		trade.setDealName("a");
		trade.setDealType("a");
		trade.setRevisionDate(null);
		trade.setRevisionName("a");
		trade.setSecurity("a");
		trade.setSellPrice(1.);
		trade.setSellQuantity(1.);
		trade.setSide("a");
		trade.setSourceListId("a");
		trade.setStatus("a");
		trade.setTradeDate(null);
		trade.setTradeId(1);
		trade.setTrader("a");
		trade.setType("a");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(tradeController.addTradeForm(trade, model)).isEqualTo("trade/add");
	}
	
	@Test
	public void testValidatedWhenNoError() throws Exception {
		//GIVEN
		Trade trade = new Trade();
		// WHEN
		when(tradeServiceMock.save(trade)).thenReturn(new Trade());
		tradeController.setTradeService(tradeServiceMock);
		//THEN
		assertThat(tradeController.validate(trade)).isEqualTo("redirect:/trade/list");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(tradeServiceMock.findById(id)).thenReturn(new Trade());
		tradeController.setTradeService(tradeServiceMock);
		// THEN
		assertThat(tradeController.showUpdateForm(id, model)).isEqualTo("trade/update");
	}
	
	@Test
	public void testUpdateTradeWhenNoError() throws Exception {
		//GIVEN
		int id = 8;
		Trade trade = new Trade();
		// WHEN
		when(tradeServiceMock.save(trade)).thenReturn(new Trade());
		tradeController.setTradeService(tradeServiceMock);
		//THEN
		assertThat(tradeController.updateTrade(id, trade)).isEqualTo("redirect:/trade/list");
	}

	@Test
	public void testDeleteBid() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(tradeServiceMock.deleteById(id)).thenReturn(new Trade());
		tradeController.setTradeService(tradeServiceMock);
		// THEN
		assertThat(tradeController.deleteTrade(id, model)).isEqualTo("redirect:/trade/list");
	}

}
