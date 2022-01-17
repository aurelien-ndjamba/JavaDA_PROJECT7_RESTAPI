package com.nnk.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override  
	protected void configure(HttpSecurity http) throws Exception {
		http 
		.authorizeRequests()
				.antMatchers("/css/**", "/js/**", "/images/**", "/", "/login", "/user/add", "/user/validate") 
				.permitAll() 
			.and()
		.authorizeRequests().antMatchers("/secure/**","/user/update/**").authenticated()
		    .and()
		.authorizeRequests().antMatchers("/secure/**","/user/update/**").hasAnyAuthority("ADMIN")
				
		.anyRequest().authenticated()
			.and()
				.formLogin() 
				.loginProcessingUrl("/login")
				.loginPage("/login") 
				.defaultSuccessUrl("/admin/home")
				.failureUrl("/login?error")
				
			.and()
				.oauth2Login()
				.loginPage("/login") 

			.and()	
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                
            .and()
				.exceptionHandling()
				.accessDeniedPage("/error")
				
            .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key("myKey")
                
				; 
	}
 
	@Bean
	public BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder(16);
	}
}
