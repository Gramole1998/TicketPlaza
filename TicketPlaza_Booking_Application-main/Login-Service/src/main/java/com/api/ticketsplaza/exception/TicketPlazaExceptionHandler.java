package com.api.ticketsplaza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TicketPlazaExceptionHandler {

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorCode(ex.getErrorCode());
		if (ex.getErrorCode().equalsIgnoreCase("INVALID_CREDENTIALS")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		// Return a ResponseEntity with the error response and HTTP status code
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

}
