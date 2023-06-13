package com.devb.usermanagement.entity.auth;

public class UserResponse {

	public Integer id;
	public String fullName;
	public String email;
	public String role;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserResponse(Integer id, String fullName, String email, String role) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
	}
	public UserResponse() {
		
	}
	
	
}
