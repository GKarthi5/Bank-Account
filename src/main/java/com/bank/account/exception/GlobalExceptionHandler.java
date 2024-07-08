package com.bank.account.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	  
	  /*protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
	           HttpHeaders headers, HttpStatusCode status, WebRequest request) {

	    List<String> details = new ArrayList<>();
	    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
	      details.add(error.getDefaultMessage());
	    }
	    AccountException error = new AccountException("Validation Failed", details);
	    return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	  }*/
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<?> resourceNotFoundException(AccountException ex,WebRequest request)
	{
		ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	}