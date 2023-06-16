package com.devb.usermanagement.service.exception;

public class Unauthorized extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public Unauthorized(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public Unauthorized(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
