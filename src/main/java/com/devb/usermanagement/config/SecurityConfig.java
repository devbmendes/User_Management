package com.devb.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("balduino").password(passwordEncoder().encode("123")).roles("ROLE_ADMIN").build();
        UserDetails user = User.withUsername("bal").password(passwordEncoder().encode("123")).roles("ROLE_USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }
    
    @SuppressWarnings("removal")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	return httpSecurity.csrf().disable()
    			.authorizeHttpRequests()
    			.requestMatchers("swagger-ui/index.html").permitAll().and()
    			.authorizeHttpRequests()
    			.requestMatchers("/**").authenticated()
    			.and().
    			build() ;  
    	}
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

}
