package com.ticketplaza.microservice.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ticketplaza.microservice.booking.dto.TicketBookingDto;
import com.ticketplaza.microservice.booking.service.TicketBookingImpl;

@RestController
@RequestMapping("/api/ticketservice")
public class BookingController {
	
	@Autowired
	TicketBookingImpl ticketBookingImpl;

	@PostMapping("/booktickets")
	public TicketBookingDto bookTickets(@RequestBody TicketBookingDto ticketBookingDto) {
		
		return ticketBookingImpl.bookTickets(ticketBookingDto);
		
	}
}
