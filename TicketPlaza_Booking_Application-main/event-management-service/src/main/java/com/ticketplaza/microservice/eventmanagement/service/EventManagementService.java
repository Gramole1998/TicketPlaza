package com.ticketplaza.microservice.eventmanagement.service;

import com.ticketplaza.microservice.eventmanagement.dto.EventDto;

public interface EventManagementService {
	
   EventDto createEvent(EventDto eventDto);
   
   void deleteEvent(long id);
   
   EventDto getEventDetails(long id);
   
   EventDto updateEventDetails(long id, EventDto eventDto);

}
