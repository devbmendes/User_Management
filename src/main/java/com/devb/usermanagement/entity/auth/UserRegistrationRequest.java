package com.devb.usermanagement.entity.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationRequest {

	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 50)
	private String firstName;
	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 50)
	private String lastName; 
	@Email
	private String email;
	@NotEmpty
	@NotBlank
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message = "Password should include: "
			+ "Has minimum 8 characters in length - "
			+"At least one uppercase English letter - "
			+"At least one lowercase English letter - "
			+"At least one digit - "
			+"At least one special character..")
	private String password;
	
	private String role;
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public UserRegistrationRequest(String firstName, String lastName, String email, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserRegistrationRequest() {

	}

}
