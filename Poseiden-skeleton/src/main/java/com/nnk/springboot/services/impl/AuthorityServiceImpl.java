package com.nnk.springboot.services.impl;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.nnk.springboot.services.IAuthorityService;

/**
 * Service AuthorityService respectant le contrat dénifi par IAuthorityService.
 */
@Service
public class AuthorityServiceImpl implements IAuthorityService{
	/**
	 * Méthode définie par IAuthorityService pour obtenir les droits d'accès d'un utilisateur qui
	 * s'identifie à partir de son usermame et son password enregistrés en base de
	 * donnée
	 * 
	 * @param Principal
	 * @return StringBuffer
	 */
	@Override
	public StringBuffer getUsernamePasswordLoginAuthority(Principal user) {
		StringBuffer Authorities = new StringBuffer();
		UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
		Authorities.append(token.getAuthorities());
		return Authorities;
	}
	
	/**
	 * Méthode définie par IAuthorityService pour obtenir les droits d'accès d'un utilisateur qui
	 * s'identifie à partir de son compte GitHub.
	 * 
	 * @param Principal
	 * @return StringBuilder
	 */
	@Override
	public StringBuffer getOauth2LoginAuthority(Principal user) {
		StringBuffer Authorities = new StringBuffer();
		OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
		Authorities.append(authToken.getAuthorities());
		return Authorities;
	}
}
