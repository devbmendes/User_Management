package com.devb.usermanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devb.usermanagement.repository.UserAppRepository;
import com.devb.usermanagement.service.exception.ObjectNotFoundException;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private UserAppRepository userAppRepository;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userAppRepository.findByEmail(username).orElseThrow(()->
				new ObjectNotFoundException("User not Found"));
			}
		};
	}

}
