package com.ticketplaza.microservice.eventmanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(EventManagementException.class)
	    public ResponseEntity<ErrorDetails> handleEventManagementException(EventManagementException exception, WebRequest request) {
	        ErrorDetails errorDetails = new ErrorDetails(
	                LocalDateTime.now(),
	                exception.getMessage(),
	                exception.getHttpStatus()
	        );
	        return new ResponseEntity<>(errorDetails, errorDetails.getStatus());
	    }

}
