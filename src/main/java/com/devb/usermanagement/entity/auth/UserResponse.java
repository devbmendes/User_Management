package com.devb.usermanagement.entity.auth;

public class UserResponse {

	private Integer id;
	private String firstName;
	private String LastName;
	public String email;
	public String role;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public UserResponse(Integer id, String firstName, String lastName, String email, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.LastName = lastName;
		this.email = email;
		this.role = role;
	}
	public UserResponse() {
		
	}
	
	
}
