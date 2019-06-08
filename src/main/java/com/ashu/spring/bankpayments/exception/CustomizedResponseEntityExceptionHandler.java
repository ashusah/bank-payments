package com.ashu.spring.bankpayments.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//I want this exception handler class to apply to all Controllers
@ControllerAdvice
//Since it is providing a response back
@RestController
public class CustomizedResponseEntityExceptionHandler
extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions
	(Exception ex, WebRequest request) throws Exception {
		//Whenver exception happens I want to return my specific bean object
		ExceptionResponse exResponse=  new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//We made a custom exception Object. Now we want to handle what happens when that exception is thrown
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptionExceptions
	(AccountNotFoundException ex, WebRequest request) throws Exception {
		//Whenver exception happens I want to return my specific bean object
		ExceptionResponse exResponse=  new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exResponse,HttpStatus.NOT_FOUND);
	}
	
	//@ResponseEntity is returned back as a response of this exception	
	@ExceptionHandler(UnsupportedInputException.class)
	public final ResponseEntity handleUnsupportedInputException
	(UnsupportedInputException ex,WebRequest request) {		
		ExceptionResponse exResponse=  new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exResponse,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
		
	}
	

}
