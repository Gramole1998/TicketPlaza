package com.ticketplaza.microservice.eventmanagement.dto;

import java.time.LocalDateTime;

import com.ticketplaza.microservice.eventmanagement.enums.EventCategory;
import com.ticketplaza.microservice.eventmanagement.enums.EventStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventDto {

	private long id;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String eventName;
	private String location;
	private String eventDescription;
	private String organizerId;
	private int totalSeats;
	private int bookedSeats;
	private EventCategory eventCategory;
	private EventStatus eventStatus;

}
