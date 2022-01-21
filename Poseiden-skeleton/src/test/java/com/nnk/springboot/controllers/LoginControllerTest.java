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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;

@SpringBootTest
public class LoginControllerTest {

	@Autowired
	private LoginController loginController;

	@Mock
	private IAuthorityService authorityServiceMock;
	@Mock
	private IInfoService infoServiceMock;

	@Test
	public void testLoginWhenAuthenticationIsNull() {
		// GIVEN
		Authentication authentication = null;
		// THEN
		assertThat(loginController.login(authentication).getViewName()).isEqualTo("login");
	}

	@Test
	public void testLoginWhenAuthenticationIsNotNull() {
		// GIVEN
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password");
		// THEN
		assertThat(loginController.login(authentication).getViewName()).isEqualTo("bidList/list");
	}

	@Test
	public void testUsersListWhenUsernamePasswordAuthenticationToken() {
		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Principal user = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");
		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(infoServiceMock.getUsernamePasswordLoginInfo(user)).thenReturn(info);
		loginController.setAuthorityService(authorityServiceMock);
		loginController.setInfoService(infoServiceMock);

		// THEN
		assertThat(loginController.usersList(user).getViewName()).isEqualTo("user/list");
		assertThat(loginController.usersList(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(loginController.usersList(user).getModel().containsKey("userInfo")).isEqualTo(true);
		assertThat(loginController.usersList(user).getModel().containsKey("users")).isEqualTo(true);
	}

	@Test
	public void testUsersListWhenOAuth2AuthenticationToken() {

		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");

		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		StringBuffer info = new StringBuffer();
		info.append("toto");
		// WHEN
		when(infoServiceMock.getOauth2LoginInfo(user)).thenReturn(info);
		loginController.setInfoService(infoServiceMock);

		// THEN
		assertThat(loginController.usersList(user).getViewName()).isEqualTo("user/list");
		assertThat(loginController.usersList(user).getModel().containsKey("userInfo")).isEqualTo(true);
		assertThat(loginController.usersList(user).getModel().containsKey("users")).isEqualTo(true);

	}

	@Test
	public void testErrorWhenUsernamePasswordAuthenticationToken() {
		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Principal user = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");
		// WHEN
		when(infoServiceMock.getUsernamePasswordLoginInfo(user)).thenReturn(info);
		loginController.setInfoService(infoServiceMock);

		// THEN
		assertThat(loginController.error(user).getViewName()).isEqualTo("403");
		assertThat(loginController.error(user).getModel().containsKey("userInfo")).isEqualTo(true);
		assertThat(loginController.error(user).getModel().containsKey("errorMsg")).isEqualTo(true);
	}

	@Test
	public void testErrorWhenOAuth2AuthenticationToken() {

		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");

		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		StringBuffer info = new StringBuffer();
		info.append("toto");
		// WHEN
		when(infoServiceMock.getOauth2LoginInfo(user)).thenReturn(info);
		loginController.setInfoService(infoServiceMock);

		// THEN
		assertThat(loginController.error(user).getViewName()).isEqualTo("403");
		assertThat(loginController.error(user).getModel().containsKey("userInfo")).isEqualTo(true);
		assertThat(loginController.error(user).getModel().containsKey("errorMsg")).isEqualTo(true);

	}
}
