package com.ticketplaza.microservice.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ticketplaza.microservice.booking.config.APIClient;
import com.ticketplaza.microservice.booking.dto.EventDto;
import com.ticketplaza.microservice.booking.dto.TicketBookingDto;
import com.ticketplaza.microservice.booking.entity.TicketBooking;
import com.ticketplaza.microservice.booking.exceptioHandling.TickedBookedException;
import com.ticketplaza.microservice.booking.exceptioHandling.TicketTypeNotFoundException;
import com.ticketplaza.microservice.booking.repository.TicketBookingRepository;

@Service
public class TicketBookingImpl implements TicketBookingService {

	@Autowired
	APIClient apiClient;

	@Autowired
	TicketBookingRepository ticketBookingRepository;

	@Override
	public TicketBookingDto bookTickets(TicketBookingDto ticketBookingDto) {
		TicketBooking ticketBooking = new TicketBooking();
		ticketBooking.setEventId(ticketBookingDto.getEventId());
		ticketBooking.setCustomerEmail(ticketBookingDto.getCustomerEmail());
		ticketBooking.setCustomerName(ticketBookingDto.getCustomerName());
		ticketBooking.setNumberOfTickets(ticketBookingDto.getNumberOfTickets());
		ticketBooking.setTicketType(ticketBookingDto.getTicketType());
		
	    
		EventDto eventDto = apiClient.getEventDetails(ticketBookingDto.getEventId());
		if(eventDto.getBookedSeats()<eventDto.getTotalSeats()) 
		{
		if(eventDto.getTicketType().containsKey(ticketBooking.getTicketType())) {
		eventDto.setBookedSeats(eventDto.getBookedSeats() + ticketBookingDto.getNumberOfTickets());
		apiClient.updateEventDetails(ticketBooking.getEventId(), eventDto);
		ticketBookingRepository.save(ticketBooking);
		}
		else {
			throw new TicketTypeNotFoundException("Type Not Found ",HttpStatus.BAD_REQUEST);
		}
		}
		else
		{
			throw new TickedBookedException("All Seat Are Booked", HttpStatus.BAD_REQUEST);
		}
		return ticketBookingDto;

	}

}
