package com.nnk.springboot.services;

import java.security.Principal;

public interface IAuthorityService {

	public StringBuffer getUsernamePasswordLoginAuthority(Principal user);

	public StringBuffer getOauth2LoginAuthority(Principal user);

}
