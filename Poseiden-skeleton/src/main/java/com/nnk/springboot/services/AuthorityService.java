package com.nnk.springboot.services;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

	public StringBuffer getUsernamePasswordLoginAuthority(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		if (token.isAuthenticated()) {
			usernameInfo.append(token.getAuthorities());
		} else {
			usernameInfo.append("NA");
		}
		return usernameInfo;
	}
	
	public StringBuffer getOauth2LoginAuthority(Principal user) {
		StringBuffer loginInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		if (authToken.isAuthenticated()) {
			loginInfo.append(authToken.getAuthorities());
		} else {
			loginInfo.append("NA");
		}
		return loginInfo;
	}
}
