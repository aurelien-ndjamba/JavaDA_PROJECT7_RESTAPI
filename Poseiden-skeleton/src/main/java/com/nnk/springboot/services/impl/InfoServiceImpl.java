package com.nnk.springboot.services.impl;

import java.security.Principal;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.nnk.springboot.services.IInfoService;

/**
 * Service InfoService respectant le contrat dénifi par IInfoService.
 */
@Service
public class InfoServiceImpl implements IInfoService{
	
	/**
	 * Méthode définie par IInfoService permettant d'obtenir le username d'un utilisateur qui
	 * s'identifie à partir de son usermame et son password enregistrés en base de
	 * donnée
	 * 
	 * @param Principal
	 * @return StringBuffer
	 */
	@Override
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
	
	/**
	 * Méthode définie par IInfoService pour obtenir le login d'un utilisateur qui
	 * s'identifie à partir de son compte GitHub.
	 * 
	 * @param Principal
	 * @return StringBuilder
	 */
	@Override
	public StringBuffer getOauth2LoginInfo(Principal user) {
		StringBuffer loginInfo = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		if (authToken.isAuthenticated()) {
			Map<String, Object> userAttributes = (authToken.getPrincipal()).getAttributes();  
			loginInfo.append(userAttributes.get("login"));
		} else {
			loginInfo.append("NA");
		}
		return loginInfo;
	}

}
