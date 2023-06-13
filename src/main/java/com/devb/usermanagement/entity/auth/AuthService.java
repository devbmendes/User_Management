package com.devb.usermanagement.entity.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devb.usermanagement.entity.Role;
import com.devb.usermanagement.service.exception.DataIntegratyViolationException;

@Service
public class AuthService {
	
	@Autowired
	private UserAppRepository userAppRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean checkUser(UserRegistrationRequest userRegistrationRequest){
		Optional<UserApp> userApp = userAppRepository.findByEmail(userRegistrationRequest.getEmail());
		Optional<UserApp> useOptional = userAppRepository.findByPassw(passwordEncoder.encode(userRegistrationRequest.getPassword()));
		
		if(useOptional.isPresent() || userApp.isPresent()) {
			throw new DataIntegratyViolationException("Password or UserName already exist");
		}
		
		return true;
	}
	
	public UserApp save(UserRegistrationRequest userRegistrationRequest) {
		
		checkUser(userRegistrationRequest);
		UserApp uApp = new UserApp();
		uApp.setEmail(userRegistrationRequest.getEmail());
		uApp.setFirstName(userRegistrationRequest.getFirstName());
		uApp.setLastName(userRegistrationRequest.getLastName());
		uApp.setPassw(passwordEncoder.encode(userRegistrationRequest.getPassword()));
		uApp.setRole(Role.USER);
		return userAppRepository.save(uApp);
	}

}
