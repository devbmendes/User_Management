package com.devb.usermanagement.entity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class UserController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserApp> registration(@RequestBody UserRegistrationRequest userRequest) {
		UserApp userApp = authService.save(userRequest);

		return ResponseEntity.status(HttpStatus.OK).body(userApp);
	}
	
	@GetMapping("/login")
	public String authentication(UserAuthRequest userAuth) {
		return "Authentication";
	}

}
