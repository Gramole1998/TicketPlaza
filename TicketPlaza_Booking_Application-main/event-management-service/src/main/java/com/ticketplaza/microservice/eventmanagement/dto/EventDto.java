package com.ticketplaza.microservice.eventmanagement.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ticketplaza.microservice.eventmanagement.enums.EventCategory;
import com.ticketplaza.microservice.eventmanagement.enums.EventStatus;

import jakarta.persistence.ElementCollection;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDate;
	private String eventName;
	private String location;
	private String eventDescription;
	private String organizerId;
	private int totalSeats;
	private int bookedSeats;
	private EventCategory eventCategory;
	private EventStatus eventStatus;
	@ElementCollection
	private Map<String,Integer> ticketType;

}
