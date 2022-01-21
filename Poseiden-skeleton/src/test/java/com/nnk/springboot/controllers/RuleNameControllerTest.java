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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;
import com.nnk.springboot.services.IRuleNameService;

@SpringBootTest
public class RuleNameControllerTest {

	@Autowired
	private RuleNameController ruleNameController;
	@Mock
	private IRuleNameService ruleNameServiceMock;
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

		RuleName ruleName = new RuleName();
		ArrayList<RuleName> ruleNames = new ArrayList<RuleName>();
		ruleNames.add(ruleName);

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(
				"username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(ruleNameServiceMock.findAll()).thenReturn(ruleNames);
		ruleNameController.setAuthorityService(authorityServiceMock);
		ruleNameController.setRuleNameService(ruleNameServiceMock);

		// THEN
		assertThat(ruleNameController.home(user).getViewName()).isEqualTo("ruleName/list");
		assertThat(ruleNameController.home(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(ruleNameController.home(user).getModel().containsKey("ruleNames")).isEqualTo(true);
		assertThat(ruleNameController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);
	}

	@Test
	public void testHomeWhenOAuth2AuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		RuleName ruleName = new RuleName();
		ArrayList<RuleName> ruleNames = new ArrayList<RuleName>();
		ruleNames.add(ruleName);
		
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
		when(ruleNameServiceMock.findAll()).thenReturn(ruleNames);
		ruleNameController.setInfoService(infoServiceMock);
		ruleNameController.setRuleNameService(ruleNameServiceMock);

		// THEN
		assertThat(ruleNameController.home(user).getViewName()).isEqualTo("ruleName/list");
		assertThat(ruleNameController.home(user).getModel().containsKey("authority")).isEqualTo(false);
		assertThat(ruleNameController.home(user).getModel().containsKey("ruleNames")).isEqualTo(true);
		assertThat(ruleNameController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);

	}
	
	
	@Test
	public void testAddRuleForm() throws Exception {
		// GIVEN
		RuleName ruleName = new RuleName();
		ruleName.setDescription("a");
		ruleName.setId(1);
		ruleName.setJson("a");
		ruleName.setName("a");
		ruleName.setSqlPart("a");
		ruleName.setSqlStr("a");
		ruleName.setTemplate("a");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(ruleNameController.addRuleForm(ruleName, model)).isEqualTo("ruleName/add");
	}
	
	@Test
	public void testValidatedWhenNoError() throws Exception {
		//GIVEN
		RuleName ruleName = new RuleName();
		// WHEN
		when(ruleNameServiceMock.save(ruleName)).thenReturn(new RuleName());
		ruleNameController.setRuleNameService(ruleNameServiceMock);
		//THEN
		assertThat(ruleNameController.validate(ruleName)).isEqualTo("redirect:/ruleName/list");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(ruleNameServiceMock.findById(id)).thenReturn(new RuleName());
		ruleNameController.setRuleNameService(ruleNameServiceMock);
		// THEN
		assertThat(ruleNameController.showUpdateForm(id, model)).isEqualTo("ruleName/update");
	}
	
	@Test
	public void testUpdateBidWhenNoError() throws Exception {
		//GIVEN
		int id = 8;
		RuleName ruleName = new RuleName();
		// WHEN
		when(ruleNameServiceMock.save(ruleName)).thenReturn(new RuleName());
		ruleNameController.setRuleNameService(ruleNameServiceMock);
		//THEN
		assertThat(ruleNameController.updateRuleName(id, ruleName)).isEqualTo("redirect:/ruleName/list");
	}

	@Test
	public void testDeleteBid() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(ruleNameServiceMock.deleteById(id)).thenReturn(new RuleName());
		ruleNameController.setRuleNameService(ruleNameServiceMock);
		// THEN
		assertThat(ruleNameController.deleteRuleName(id, model)).isEqualTo("redirect:/ruleName/list");
	}
}
