package com.devb.usermanagement.entity.auth;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devb.usermanagement.entity.Role;
import com.devb.usermanagement.service.JwtService;
import com.devb.usermanagement.service.exception.DataIntegratyViolationException;

@Service
public class AuthService {
	
	private final UserAppRepository userAppRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	
	
	public AuthService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager, JwtService jwtService) {
		super();
		this.userAppRepository = userAppRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	public boolean checkUser(UserRegistrationRequest userRegistrationRequest){
		Optional<UserApp> userApp = userAppRepository.findByEmail(userRegistrationRequest.getEmail());
		Optional<UserApp> useOptional = userAppRepository.findByPassw(userRegistrationRequest.getPassword());
		
		if(useOptional.isPresent() || userApp.isPresent()) {
			throw new DataIntegratyViolationException("Password or Email already exist");
		}
		return true;
	}
	
	public UserResponse save(UserRegistrationRequest userRegistrationRequest) {
		
		checkUser(userRegistrationRequest);
		UserApp uApp = new UserApp();
		uApp.setEmail(userRegistrationRequest.getEmail());
		uApp.setFirstName(userRegistrationRequest.getFirstName());
		uApp.setLastName(userRegistrationRequest.getLastName());
		uApp.setPassw(passwordEncoder.encode(userRegistrationRequest.getPassword()));
		uApp.setRole(Role.USER);
		UserApp respApp = userAppRepository.save(uApp);
		return new UserResponse(respApp.getId(),respApp.getFirstName()+ " "+respApp.getLastName(),respApp.getEmail(),respApp.getRole().name());
	}
	
	public LoginResponse login (UserAuthRequest userAuthRequest) {
		 Authentication authentication = authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(userAuthRequest.getEmail(), userAuthRequest.getPassword()));
		 if(authentication.isAuthenticated()) {
			 return new LoginResponse(authentication.getName(), jwtService.generateToken(authentication.getName())) ;
		 }else {
			 throw new DataIntegratyViolationException("Credentials invalid");
		 }
	}

}
