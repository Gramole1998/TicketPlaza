package com.ticketplaza.microservice.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketplaza.microservice.booking.config.APIClient;
import com.ticketplaza.microservice.booking.dto.EventDto;
import com.ticketplaza.microservice.booking.dto.TicketBookingDto;
import com.ticketplaza.microservice.booking.entity.TicketBooking;
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
		ticketBooking.setCustomerEmail(ticketBookingDto.getCustomerName());
		ticketBooking.setCustomerName(ticketBookingDto.getCustomerName());
		ticketBooking.setNumberOfTickets(ticketBookingDto.getNumberOfTickets());
		

		EventDto eventDto = apiClient.getEventDetails(ticketBookingDto.getEventId());

		eventDto.setBookedSeats(eventDto.getBookedSeats() + ticketBookingDto.getNumberOfTickets());
		apiClient.updateEventDetails(ticketBooking.getEventId(), eventDto);
		ticketBookingRepository.save(ticketBooking);
		return ticketBookingDto;

	}

}
