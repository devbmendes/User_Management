package com.devb.book_store.DTO;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AuthorDTO {

	@NotEmpty(message="First NAME field is required")
	@NotBlank
	@Length(min = 3, max = 100, message = "Must be between 3 to 100 charater")
	private String firstname;
	@NotEmpty(message="Field LAST NAME field is required")
	@NotBlank
	@Length(min = 3, max = 100, message = "Must be between 3 to 100 charater")
	private String lastname;
	@Email(message = "Insert a valid email")
	@NotEmpty
	@NotBlank
	private String email;

	public AuthorDTO() {

	}

	public AuthorDTO(String firstname, String lastname, String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
