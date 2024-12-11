package com.ticketplaza.microservice.eventmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ticketplaza.microservice.eventmanagement.dto.EventDto;
import com.ticketplaza.microservice.eventmanagement.entity.Event;
import com.ticketplaza.microservice.eventmanagement.exception.EventManagementException;
import com.ticketplaza.microservice.eventmanagement.repository.EventManagementRepository;

@Service
public class EventManagementServiceImpl implements EventManagementService {

	private final EventManagementRepository eventManagementRepository;

	@Autowired
	public EventManagementServiceImpl(EventManagementRepository eventManagementRepository) {
		this.eventManagementRepository = eventManagementRepository;
	}

	@Override
	public EventDto createEvent(EventDto eventDto) {
		Event event = convertToEntity(eventDto);
		Event savedEvent = eventManagementRepository.save(event);
		return convertToDto(savedEvent);

	}

	private Event convertToEntity(EventDto eventDto) {
		Event event = new Event();
		event.setId(eventDto.getId());
		event.setStartDate(eventDto.getStartDate());
		event.setEndDate(eventDto.getEndDate());
		event.setEventName(eventDto.getEventName());
		event.setLocation(eventDto.getLocation());
		event.setEventDescription(eventDto.getEventDescription());
		event.setOrganizerId(eventDto.getOrganizerId());
		event.setTotalSeats(eventDto.getTotalSeats());
		event.setBookedSeats(eventDto.getBookedSeats());
		event.setEventCategory(eventDto.getEventCategory());
		event.setEventStatus(eventDto.getEventStatus());
		return event;
	}

	private EventDto convertToDto(Event event) {
		EventDto eventDto = new EventDto();
		eventDto.setId(event.getId());
		eventDto.setStartDate(event.getStartDate());
		eventDto.setEndDate(event.getEndDate());
		eventDto.setEventName(event.getEventName());
		eventDto.setLocation(event.getLocation());
		eventDto.setEventDescription(event.getEventDescription());
		eventDto.setOrganizerId(event.getOrganizerId());
		eventDto.setTotalSeats(event.getTotalSeats());
		eventDto.setBookedSeats(event.getBookedSeats());
		eventDto.setEventCategory(event.getEventCategory());
		eventDto.setEventStatus(event.getEventStatus());
		return eventDto;
	}

	@Override
	public void deleteEvent(long id) {

		Event event = eventManagementRepository.findById(id)
				.orElseThrow(() -> new EventManagementException("Invalid Event ID", HttpStatus.BAD_REQUEST));

		eventManagementRepository.delete(event);

	}

	@Override
	public EventDto getEventDetails(long id) {
		Event eventDetails = eventManagementRepository.findById(id)
				.orElseThrow(() -> new EventManagementException("Invalid Event ID", HttpStatus.BAD_REQUEST));
		EventDto eventDetailsDto = convertToDto(eventDetails);
		return eventDetailsDto;
	}

	@Override
	public EventDto updateEventDetails(long id, EventDto updateEventDto) {
		Event existingEvent = eventManagementRepository.findById(id)
				.orElseThrow(() -> new EventManagementException("Invalid Event ID", HttpStatus.BAD_REQUEST));
	
		if (updateEventDto.getStartDate() != null)
			existingEvent.setStartDate(updateEventDto.getStartDate());
		if (updateEventDto.getEndDate() != null)
			existingEvent.setEndDate(updateEventDto.getEndDate());
		if (updateEventDto.getEventName() != null)
			existingEvent.setEventName(updateEventDto.getEventName());
		if (updateEventDto.getLocation() != null)
			existingEvent.setLocation(updateEventDto.getLocation());
		if (updateEventDto.getEventDescription() != null)
			existingEvent.setEventDescription(updateEventDto.getEventDescription());
		if (updateEventDto.getTotalSeats() > 0)
			existingEvent.setTotalSeats(updateEventDto.getTotalSeats());
		if (updateEventDto.getEventCategory() != null)
			existingEvent.setEventCategory(updateEventDto.getEventCategory());
		if (updateEventDto.getEventStatus() != null)
			existingEvent.setEventStatus(updateEventDto.getEventStatus());
		if (updateEventDto.getBookedSeats()>0)
			existingEvent.setBookedSeats(updateEventDto.getBookedSeats());
		
		Event updatedEvent= eventManagementRepository.save(existingEvent);
		return convertToDto(updatedEvent);
	}

}
