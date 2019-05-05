package com.ashu.spring.bankpayments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

	public AccountNotFoundException() {
		// TODO Auto-generated constructor stub
		super();
	}
}