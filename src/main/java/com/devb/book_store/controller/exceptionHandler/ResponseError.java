package com.devb.book_store.controller.exceptionHandler;

import java.time.LocalDate;

public class ResponseError {
	
	private LocalDate localDate;
	private Integer codeError;
	private String msg;
	
	
	public LocalDate getLocalDate() {
		return localDate;
	}
	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}
	public Integer getCodeError() {
		return codeError;
	}
	public void setCodeError(Integer codeError) {
		this.codeError = codeError;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ResponseError() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseError(LocalDate localDate, Integer codeError, String msg) {
		super();
		this.localDate = localDate;
		this.codeError = codeError;
		this.msg = msg;
	}

}
