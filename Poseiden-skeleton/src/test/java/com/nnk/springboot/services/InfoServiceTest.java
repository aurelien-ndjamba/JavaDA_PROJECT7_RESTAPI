package com.nnk.springboot.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.nnk.springboot.services.impl.InfoServiceImpl;

@SpringBootTest
public class InfoServiceTest {

	@Autowired
	private InfoServiceImpl infoService;

	@Test
	public void getUsernamePasswordLoginInfoTest() {
		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		
		// WHEN
		User u = new User("username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// THEN
		assertThat(user instanceof UsernamePasswordAuthenticationToken).isEqualTo(true);
		assertThat(user instanceof OAuth2AuthenticationToken).isEqualTo(false);
		assertThat(infoService.getUsernamePasswordLoginInfo(user).toString()).isEqualTo("username");
	}

	@Test
	public void getOauth2LoginInfoTest() {

		// GIVEN
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");
		// WHEN
		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		// THEN
		assertThat(user instanceof UsernamePasswordAuthenticationToken).isEqualTo(false);
		assertThat(user instanceof OAuth2AuthenticationToken).isEqualTo(true);
		assertThat(infoService.getOauth2LoginInfo(user).toString()).isEqualTo("toto");
	}

}
