package com.ticketplaza.microservice.booking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class TicketBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long eventId;
	private String customerName;
	private String customerEmail;
	private int numberOfTickets;
}
