package com.devb.book_store.controller.exceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends ResponseError{
	
	private List<FieldError> errors = new ArrayList<>();

	public ValidationError(List<FieldError> errors) {
		super();
		this.errors = errors;
	}

	public ValidationError() {
		super();
		
	}

	public ValidationError(LocalDate localDate, Integer codeError, String msg) {
		super(localDate, codeError, msg);
	}

	public void addError(String fieldname, String message) {
		this.errors.add(new FieldError(fieldname, message));
	}

	public List<FieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<FieldError> errors) {
		this.errors = errors;
	}
	

}
