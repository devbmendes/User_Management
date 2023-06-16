package com.devb.usermanagement.entity.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserAuthRequest {
	
	@Email
	private String email;
	
	@NotEmpty
	@NotNull
	@Size(min = 8, max = 50)
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserAuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public UserAuthRequest() {
		
	}
	
	

}
