package com.ashu.spring.bankpayments.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date date;
	private String message;
	private String detail;
	public ExceptionResponse(Date date, String message, String detail) {
		super();
		this.date = date;
		this.message = message;
		this.detail = detail;
	}
	public Date getDate() {
		return date;
	}
	public String getMessage() {
		return "An exception occurred:  "+message;
	}
	public String getDetail() {
		return detail;
	}
	
}
