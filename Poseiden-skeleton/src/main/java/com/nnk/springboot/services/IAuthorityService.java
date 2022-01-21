package com.nnk.springboot.services;

import java.security.Principal;

/**
 * Interface IAuthorityService
 */
public interface IAuthorityService {

	/**
	 * Méthode abstraite pour obtenir les droits d'accès d'un utilisateur
	 * s'identifiant à partir de son usermame et son password enregistrés en base de
	 * donnée
	 * 
	 * @param Principal
	 * @return StringBuilder
	 */
	public StringBuffer getUsernamePasswordLoginAuthority(Principal user);

	/**
	 * Méthode abstraite pour obtenir les droits d'accès d'un utilisateur
	 * qui s'identifie à partir de son compte GitHub.
	 * 
	 * @param Principal
	 * @return StringBuilder
	 */
	public StringBuffer getOauth2LoginAuthority(Principal user);

}
