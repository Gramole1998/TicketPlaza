package com.ticketplaza.microservice.eventmanagement.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Map;

import com.ticketplaza.microservice.eventmanagement.enums.EventCategory;
import com.ticketplaza.microservice.eventmanagement.enums.EventStatus;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EventDetails")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Enumerated(EnumType.STRING)
	private EventCategory eventCategory;
	@Enumerated(EnumType.STRING)
	private EventStatus eventStatus;
	@ElementCollection
    @CollectionTable(name = "Ticket_type")
    @MapKeyColumn(name = "type")
    @Column(name = "price")
	private Map<String,Integer> ticketType;
}
