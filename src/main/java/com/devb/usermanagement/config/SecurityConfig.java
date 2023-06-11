package com.devb.usermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {
	
	@SuppressWarnings("removal")
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		
	    final JwtRequestFilter jwtRequestFilter = null;
		final AuthenticationProvider authenticationProvider = null;
		
		
		
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);

		
		return http.build();
	}
}
