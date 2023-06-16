package com.devb.usermanagement.entity.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		authService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UserResponse> updateRole(@PathVariable Integer id){
		UserResponse userResponse = authService.updateRole(id);
		return ResponseEntity.status(HttpStatus.OK).body(userResponse);
	}

}
