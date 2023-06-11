package com.devb.usermanagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devb.usermanagement.entity.UserAuthRequest;
import com.devb.usermanagement.entity.UserRegistrationRequest;

@RestController
@RequestMapping("/v1/auth")
public class UserController {
	
	@PostMapping
	public String registration(UserRegistrationRequest userRequest) {
		return "Registration";
	}
	
	public String authentication(UserAuthRequest userAuth) {
		return "Authentication";
	}

}
