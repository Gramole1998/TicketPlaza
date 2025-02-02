package com.ticketplaza.microservice.booking.exceptioHandling;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TicketTypeNotFoundException extends RuntimeException {

	private String Message; 
	private HttpStatus status;
}
