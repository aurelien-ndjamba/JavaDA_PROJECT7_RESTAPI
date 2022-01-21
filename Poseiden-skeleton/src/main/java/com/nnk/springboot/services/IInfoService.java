package com.nnk.springboot.services;

import java.security.Principal;

public interface IInfoService {
	
	public StringBuffer getUsernamePasswordLoginInfo(Principal user) ;
	
	public StringBuffer getOauth2LoginInfo(Principal user) ;

}
