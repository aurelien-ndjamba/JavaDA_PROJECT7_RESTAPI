package com.nnk.springboot.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.ICurvePointService;
import com.nnk.springboot.services.IInfoService;

@SpringBootTest
public class CurveControllerTest {
	
	@Autowired
	private CurveController curveController;
	@Mock
	private ICurvePointService curvePointServiceMock;
	@Mock
	private IAuthorityService authorityServiceMock;
	@Mock
	private IInfoService infoServiceMock;
	
	@Test
	public void testHomeWhenUsernamePasswordAuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		CurvePoint curvePoint = new CurvePoint();
		ArrayList<CurvePoint> curvePoints = new ArrayList<CurvePoint>();
		curvePoints.add(curvePoint);

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(
				"username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(curvePointServiceMock.findAll()).thenReturn(curvePoints);
		curveController.setAuthorityService(authorityServiceMock);
		curveController.setCurvePointService(curvePointServiceMock);

		// THEN
		assertThat(curveController.home(user).getViewName()).isEqualTo("curvePoint/list");
		assertThat(curveController.home(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(curveController.home(user).getModel().containsKey("curvePoints")).isEqualTo(true);
		assertThat(curveController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);
	}

	@Test
	public void testHomeWhenOAuth2AuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		CurvePoint curvePoint = new CurvePoint();
		ArrayList<CurvePoint> curvePoints = new ArrayList<CurvePoint>();
		curvePoints.add(curvePoint);
		
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("login", "toto");
		attributes.put("nameAttributeKey", "nameAttributeKey");
		// WHEN
		OAuth2User oAuth2User = new DefaultOAuth2User(authorities, attributes, "nameAttributeKey");
		Principal user = new OAuth2AuthenticationToken(oAuth2User, authorities, "authorizedClientRegistrationId");

		// WHEN
		when(infoServiceMock.getOauth2LoginInfo(user)).thenReturn(authority);
		when(curvePointServiceMock.findAll()).thenReturn(curvePoints);
		curveController.setInfoService(infoServiceMock);
		curveController.setCurvePointService(curvePointServiceMock);

		// THEN
		assertThat(curveController.home(user).getViewName()).isEqualTo("curvePoint/list");
		assertThat(curveController.home(user).getModel().containsKey("authority")).isEqualTo(false);
		assertThat(curveController.home(user).getModel().containsKey("curvePoints")).isEqualTo(true);
		assertThat(curveController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);

	}
	
	@Test
	public void testAddCurvePointForm() throws Exception {
		// GIVEN
		CurvePoint curve = new CurvePoint();
		curve.setAsOfDate(null);
		curve.setCreationDate(null);
		curve.setCurveId(1);
		curve.setId(1);
		curve.setTerm(1.0);
		curve.setValue(1.0);
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(curveController.addCurvePointForm(curve, model)).isEqualTo("curvePoint/add");
	}
	
	@Test
	public void testValidateWhenFieldErrorIscurveId() throws Exception {
		//GIVEN
		CurvePoint curve = new CurvePoint();
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("curveId", "defaultMessage1");
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(curveController.validate(curve, result, model)).isEqualTo("curvePoint/add");
	}
	
	@Test
	public void testValidatedWhenNoError() throws Exception {
		//GIVEN
		CurvePoint curve = new CurvePoint();
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		Model model = new ExtendedModelMap();
		// WHEN
		when(curvePointServiceMock.save(curve)).thenReturn(new CurvePoint());
		curveController.setCurvePointService(curvePointServiceMock);
		//THEN
		assertThat(curveController.validate(curve, result, model)).isEqualTo("redirect:/curvePoint/list");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(curvePointServiceMock.findById(id)).thenReturn(new CurvePoint());
		curveController.setCurvePointService(curvePointServiceMock);
		// THEN
		assertThat(curveController.showUpdateForm(id, model)).isEqualTo("curvePoint/update");
	}

	@Test
	public void testUpdateBidWhenFieldErrorIscurveId() throws Exception {
		//GIVEN
		int id = 8;
		CurvePoint curve = new CurvePoint();
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("curveId", "defaultMessage1");
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(curveController.updateCurvePoint(id, curve, result, model)).isEqualTo("curvePoint/update");
	}
	
	@Test
	public void testUpdateBidWhenNoError() throws Exception {
		//GIVEN
		int id = 8;
		CurvePoint curve = new CurvePoint();
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		Model model = new ExtendedModelMap();
		// WHEN
		when(curvePointServiceMock.save(curve)).thenReturn(new CurvePoint());
		curveController.setCurvePointService(curvePointServiceMock);
		//THEN
		assertThat(curveController.updateCurvePoint(id, curve, result, model)).isEqualTo("redirect:/curvePoint/list");
	}

	@Test
	public void testDeleteBid() throws Exception {
		// GIVEN
		int id = 8;
		// WHEN
		when(curvePointServiceMock.deleteById(id)).thenReturn(new CurvePoint());
		curveController.setCurvePointService(curvePointServiceMock);
		// THEN
		assertThat(curveController.deleteCurvePoint(id)).isEqualTo("redirect:/curvePoint/list");
	}

}
