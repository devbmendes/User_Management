package com.devb.usermanagement.entity.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
public class UserController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> registration(@Valid @RequestBody UserRegistrationRequest userRequest) {
		
		UserResponse userApp = authService.save(userRequest);

		return ResponseEntity.status(HttpStatus.OK).body(userApp);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authentication(
		@Valid @RequestBody UserAuthRequest userAuth) {
		LoginResponse loginResponse = authService.login(userAuth);
		return ResponseEntity.ok(loginResponse);
	}
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<UserResponse>> getAll(){
		List<UserResponse> list = authService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

}
