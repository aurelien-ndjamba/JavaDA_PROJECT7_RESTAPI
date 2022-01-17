package com.nnk.springboot.services;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service InfoService
 */
@Service
public class InfoService {
	
	public StringBuffer getUsernamePasswordLoginInfo(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		if (token.isAuthenticated()) {
			User u = (User) token.getPrincipal();
			usernameInfo.append(u.getUsername());
		} else {
			usernameInfo.append("NA");
		}
		return usernameInfo;
	}
	
	public StringBuffer getOauth2LoginInfo(Principal user) {
		StringBuffer loginInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		if (authToken.isAuthenticated()) {
			Map<String, Object> userAttributes = (authToken.getPrincipal()).getAttributes(); //(DefaultOAuth2User) 
			loginInfo.append(userAttributes.get("login"));
		} else {
			loginInfo.append("NA");
		}
		return loginInfo;
	}

}
