package com.devb.book_store.controller.exceptionHandler;

import java.io.Serializable;

public class FieldError implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fieldname;
	private String message;
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public FieldError(String fieldname, String message) {
		super();
		this.fieldname = fieldname;
		this.message = message;
	}
	public FieldError() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
