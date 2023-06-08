package com.devb.book_store.controller.exceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devb.book_store.service.exception.DataIntegratyViolationException;
import com.devb.book_store.service.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<ResponseError> objectNotFoundException(ObjectNotFoundException ex) {
		ResponseError responseError = new ResponseError(LocalDate.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);

	}

	@ExceptionHandler(DataIntegratyViolationException.class)
	public ResponseEntity<ResponseError> integrityViolation(DataIntegratyViolationException ex) {
		ResponseError responseError = new ResponseError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> validationFields(MethodArgumentNotValidException ex) {
		ValidationError validationError = new ValidationError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),
				"Validations Field Errors");
		
		List<FieldError> list = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();
		
		for (org.springframework.validation.FieldError fieldError : bindingResult.getFieldErrors()) {
			FieldError fError = new FieldError();
			fError.setFieldname(fieldError.getField());
			fError.setMessage(fieldError.getDefaultMessage());
			list.add(fError);
		}

		validationError.setErrors(list);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);

	}
	
	@ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResponseError> methodNotAllowed(org.springframework.web.HttpRequestMethodNotSupportedException ex) {
		ResponseError respError = new ResponseError(LocalDate.now(), HttpStatus.METHOD_NOT_ALLOWED.value(),
				ex.getMessage()+" to this endpoint");

		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(respError);

	}

}
