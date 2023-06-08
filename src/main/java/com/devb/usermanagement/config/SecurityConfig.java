package com.devb.usermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.withUsername("balduino").password("123").roles("ROLE_ADMIN").build();
		UserDetails user = User.withUsername("bal").password("123").roles("ROLE_USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
	}

}
