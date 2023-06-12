package com.devb.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

		
@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
		private JwtRequestFilter jwtRequestFilter;
		private AuthenticationProvider authenticationProvider;
		
		public SecurityConfig(JwtRequestFilter jwtRequestFilter, AuthenticationProvider authenticationProvider) {
			this.jwtRequestFilter = jwtRequestFilter;
			this.authenticationProvider = authenticationProvider;
		}
	    
	    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        
	    	return http
	                .authorizeHttpRequests(auth -> auth
	                   .requestMatchers("/swagger-ui/*",
	                		   "/v1/store/author/all",
	                		   "/v1/auth/login",
	                		   "/v1/auth/register",
	                		   "/h2**").permitAll()
	                )
	                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	                .authenticationProvider(authenticationProvider)
	                .addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class)
	                .build();
	        }
	
}


