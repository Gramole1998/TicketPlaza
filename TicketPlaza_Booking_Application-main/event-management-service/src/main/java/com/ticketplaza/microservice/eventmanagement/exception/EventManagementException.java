package com.ticketplaza.microservice.eventmanagement.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventManagementException extends RuntimeException {
	
	private String message;
	private HttpStatus httpStatus;

}
