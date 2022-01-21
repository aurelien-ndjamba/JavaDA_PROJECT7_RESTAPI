package com.nnk.springboot.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.IUserService;

@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private UserController userController;
	
	@Mock
	private IUserService userServiceMock;
	
	@Test
	public void testAddUserForm() throws Exception {
		// GIVEN
		User user = new User();
		user.setRole("a");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		Model model = new ExtendedModelMap();
		// THEN
		assertThat(userController.addUserForm(authentication, model)).isEqualTo("user/add");
	}
	
	@Test
	public void testValidateWhenFieldErrorIsUserName() throws Exception {
		//GIVEN
		User user = new User();
		user.setRole("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("username", "defaultMessage1");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("user/add");
	}
	
	@Test
	public void testValidateWhenFieldErrorIsPassword() throws Exception {
		//GIVEN
		User user = new User();
		user.setUsername("a");
		user.setPassword("a");
		user.setRole("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("password", "defaultMessage1");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("user/add");
	}
	
	@Test
	public void testValidateWhenFieldErrorIsFullName() throws Exception {
		//GIVEN
		User user = new User();
		user.setUsername("a");
		user.setPassword("a");
		user.setRole("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("fullname", "defaultMessage1");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("user/add");
	}
	
	@Test
	public void testValidateWhenFieldErrorIsRole() throws Exception {
		//GIVEN
		User user = new User();
		user.setRole("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.rejectValue("role", "defaultMessage1");
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("user/add");
	}
	
	@Test
	public void testValidatedWhenAuthenticationIsnull() throws Exception {
		//GIVEN
		User user = new User();
		user.setUsername("a");
		user.setPassword("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		Model model = new ExtendedModelMap();
		
		Authentication authentication = null;
		// WHEN
		when(userServiceMock.save(user)).thenReturn(new User());
		userController.setUserService(userServiceMock);
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("/home");
	}
	
	@Test
	public void testValidatedWhenNoError() throws Exception {
		//GIVEN
		User user = new User();
		user.setUsername("a");
		user.setPassword("a");
		user.setRole("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		Model model = new ExtendedModelMap();
		
		Collection<GrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", authorities);
		// WHEN
		when(userServiceMock.save(user)).thenReturn(new User());
		userController.setUserService(userServiceMock);
		//THEN
		assertThat(userController.validate(user, result, model, authentication)).isEqualTo("redirect:/secure/article-details");
	}

	@Test
	public void testShowUpdateForm() throws Exception {
		// GIVEN
		User user = new User();
		user.setUsername("a");
		user.setId(1);
		user.setPassword("a");
		user.setRole("a");
		user.setFullname("a");
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(userServiceMock.findById(id)).thenReturn(user);
		userController.setUserService(userServiceMock);
		// THEN
		assertThat(userController.showUpdateForm(id, model)).isEqualTo("user/update");
	}

	@Test
	public void testUpdateUserWhenError() throws Exception {
		//GIVEN
		int id = 8;
		User user = new User();
		user.setUsername("a");
		user.setId(1);
		user.setPassword("a");
		user.setRole("a");
		user.setFullname("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		result.addError(new ObjectError("error", "defaultMessage1")); 
		Model model = new ExtendedModelMap();
		//THEN
		assertThat(userController.updateUser(id, user, result, model)).isEqualTo("user/update");
	}
	
	@Test
	public void testUpdateUserWhenNoError() throws Exception {
		//GIVEN
		int id = 8;
		User user = new User();
		user.setUsername("a");
		user.setId(1);
		user.setPassword("a");
		user.setRole("a");
		user.setFullname("a");
		Map<String,String> target = new HashMap<String,String>();
		BindingResult result = new MapBindingResult(target , "name");
		Model model = new ExtendedModelMap();
		// WHEN
		when(userServiceMock.save(user)).thenReturn(new User());
		userController.setUserService(userServiceMock);
		//THEN
		assertThat(userController.updateUser(id, user, result, model)).isEqualTo("redirect:/secure/article-details");
	}

	@Test
	public void testDeleteUser() throws Exception {
		// GIVEN
		User user = new User();
		user.setUsername("a");
		user.setId(1);
		user.setPassword("a");
		user.setRole("a");
		user.setFullname("a");
		int id = 8;
		Model model = new ExtendedModelMap();
		// WHEN
		when(userServiceMock.findById(id)).thenReturn(user);
		userController.setUserService(userServiceMock);
		// THEN
		assertThat(userController.deleteUser(id, model)).isEqualTo("redirect:/secure/article-details");
	}

}
