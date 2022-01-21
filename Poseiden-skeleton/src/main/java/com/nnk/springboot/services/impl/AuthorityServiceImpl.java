package com.nnk.springboot.services.impl;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.nnk.springboot.services.IAuthorityService;

/**
 * Service AuthorityService
 */
@Service
public class AuthorityServiceImpl implements IAuthorityService{

	@Override
	public StringBuffer getUsernamePasswordLoginAuthority(Principal user) {
		StringBuffer usernameInfo = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		usernameInfo.append(token.getAuthorities());
		return usernameInfo;
	}

	@Override
	public StringBuffer getOauth2LoginAuthority(Principal user) {
		StringBuffer loginInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		loginInfo.append(authToken.getAuthorities());
		return loginInfo;
	}
}
