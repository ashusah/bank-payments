package com.ashu.spring.bankpayments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnsupportedInputException extends RuntimeException {
	public UnsupportedInputException(String message) {
		super(message);
	}

}
