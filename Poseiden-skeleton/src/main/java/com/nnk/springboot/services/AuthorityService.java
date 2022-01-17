package com.nnk.springboot.services;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Service AuthorityService
 */
@Service
public class AuthorityService {

	public StringBuffer getUsernamePasswordLoginAuthority(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		usernameInfo.append(token.getAuthorities());
		return usernameInfo;
	}

	public StringBuffer getOauth2LoginAuthority(Principal user) {
		StringBuffer loginInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		loginInfo.append(authToken.getAuthorities());
		return loginInfo;
	}
}
