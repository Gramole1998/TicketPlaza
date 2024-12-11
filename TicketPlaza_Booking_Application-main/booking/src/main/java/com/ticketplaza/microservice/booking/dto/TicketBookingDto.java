package com.ticketplaza.microservice.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketBookingDto {

	private long id;
	private long eventId;
	private String customerName;
	private String customerEmail;
	private int numberOfTickets;
}
