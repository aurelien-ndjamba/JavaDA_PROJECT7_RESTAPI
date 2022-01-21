package com.nnk.springboot.services;

import java.security.Principal;

/**
 * Interface IInfoService
 */
public interface IInfoService {
	
	/**
	 * Méthode abtraite pour obtenir le username d'un utilisateur qui
	 * s'identifie à partir de son password enregistré en base de
	 * donnée.
	 * 
	 * @param Principal
	 * @return StringBuffer
	 */
	public StringBuffer getUsernamePasswordLoginInfo(Principal user) ;
	
	/**
	 * Méthode abstraite pour obtenir le login d'un utilisateur qui
	 * s'identifie à partir à partir de son compte GitHub.
	 * 
	 * @param Principal
	 * @return StringBuffer
	 */
	public StringBuffer getOauth2LoginInfo(Principal user) ;

}
