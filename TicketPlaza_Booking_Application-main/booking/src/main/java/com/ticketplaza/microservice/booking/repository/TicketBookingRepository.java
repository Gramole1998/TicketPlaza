package com.ticketplaza.microservice.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketplaza.microservice.booking.entity.TicketBooking;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {

}
