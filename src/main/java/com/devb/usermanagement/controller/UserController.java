package com.devb.usermanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devb.usermanagement.entity.user.UserAuthRequest;
import com.devb.usermanagement.entity.user.UserRegistrationRequest;

@RestController
@RequestMapping("/v1/auth")
public class UserController {
	
	@PostMapping("/register")
	public String registration(UserRegistrationRequest userRequest) {
		return "Registration";
	}
	
	@GetMapping("/login")
	public String authentication(UserAuthRequest userAuth) {
		return "Authentication";
	}

}
