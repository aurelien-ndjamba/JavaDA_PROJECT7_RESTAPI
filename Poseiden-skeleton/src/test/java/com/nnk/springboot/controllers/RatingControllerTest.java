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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.IAuthorityService;
import com.nnk.springboot.services.IInfoService;
import com.nnk.springboot.services.IRatingService;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {
	
	@Autowired
	private RatingController ratingController;
	
	@Mock
	private IRatingService ratingServiceMock;
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

		Rating rating = new Rating();
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.add(rating);

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));

		org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(
				"username", "password", true, true, true, true, authorities);
		Principal user = new UsernamePasswordAuthenticationToken(u, "passwordAdmin", authorities);

		// WHEN
		when(authorityServiceMock.getUsernamePasswordLoginAuthority(user)).thenReturn(authority);
		when(ratingServiceMock.findAll()).thenReturn(ratings);
		ratingController.setAuthorityService(authorityServiceMock);
		ratingController.setRatingService(ratingServiceMock);

		// THEN
		assertThat(ratingController.home(user).getViewName()).isEqualTo("rating/list");
		assertThat(ratingController.home(user).getModel().containsKey("authority")).isEqualTo(true);
		assertThat(ratingController.home(user).getModel().containsKey("ratings")).isEqualTo(true);
		assertThat(ratingController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);
	}

	@Test
	public void testHomeWhenOAuth2AuthenticationToken() throws Exception {
		// GIVEN
		StringBuffer authority = new StringBuffer();
		authority.append("ADMIN");
		StringBuffer info = new StringBuffer();
		info.append("toto");

		Rating rating = new Rating();
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		ratings.add(rating);
		
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
		when(ratingServiceMock.findAll()).thenReturn(ratings);
		ratingController.setInfoService(infoServiceMock);
		ratingController.setRatingService(ratingServiceMock);

		// THEN
		assertThat(ratingController.home(user).getViewName()).isEqualTo("rating/list");
		assertThat(ratingController.home(user).getModel().containsKey("authority")).isEqualTo(false);
		assertThat(ratingController.home(user).getModel().containsKey("ratings")).isEqualTo(true);
		assertThat(ratingController.home(user).getModel().containsKey("userInfo")).isEqualTo(true);

	}
	
	@Test
	public void testAddBidForm() throws Exception {
		// GIVEN
		Rating rating = new Rating();
		rating.setFitchRating("a");
		rating.setId(1);
		rating.setMoodysRating("a");
		rating.setOrderNumber(1);
		rating.setSandPRating("a");
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(ratingController.addRatingForm(rating, model)).isEqualTo("rating/add");
	}
	
	@Test
	public void testValidate() throws Exception {
		//GIVEN
		Rating rating = new Rating();
		// WHEN
				when(ratingServiceMock.save(rating)).thenReturn(new Rating());
				ratingController.setRatingService(ratingServiceMock);
		//THEN
		assertThat(ratingController.validate(rating)).isEqualTo("redirect:/rating/list");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(ratingServiceMock.findById(id)).thenReturn(new Rating());
		ratingController.setRatingService(ratingServiceMock);
		// THEN
		assertThat(ratingController.showUpdateForm(id, model)).isEqualTo("rating/update");
	}
	
	@Test
	public void testUpdateBid() throws Exception {
		//GIVEN
		int id = 8;
		Rating Rating = new Rating();
		// WHEN
		when(ratingServiceMock.save(Rating)).thenReturn(new Rating());
		ratingController.setRatingService(ratingServiceMock);
		//THEN
		assertThat(ratingController.updateRating(id, Rating)).isEqualTo("redirect:/rating/list");
	}

	@Test
	public void testDeleteBid() throws Exception {
		// GIVEN
		int id = 8;
		// WHEN
		when(ratingServiceMock.deleteById(id)).thenReturn(new Rating());
		ratingController.setRatingService(ratingServiceMock);
		// THEN
		assertThat(ratingController.deleteRating(id)).isEqualTo("redirect:/rating/list");
	}

}
