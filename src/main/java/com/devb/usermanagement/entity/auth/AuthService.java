package com.devb.usermanagement.entity.auth;

import org.springframework.stereotype.Service;

import com.devb.usermanagement.entity.UserApp;
import com.devb.usermanagement.repository.UserAppRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	UserAppRepository userAppRepository;
	
	public UserApp save(UserRegistrationRequest userRegistrationRequest) {
		UserApp uApp = new UserApp();
		uApp.setEmail(userRegistrationRequest.getEmail());
		uApp.setFirstName(userRegistrationRequest.getFirstName());
		uApp.setLastName(userRegistrationRequest.getLastName());
		uApp.setPassw(userRegistrationRequest.getPassword());
		return userAppRepository.save(uApp);
	}

}
