package com.ticketplaza.microservice.booking.exceptioHandling;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class TickedBookedException extends RuntimeException{
	
	private String message;
	private HttpStatus status;
}
