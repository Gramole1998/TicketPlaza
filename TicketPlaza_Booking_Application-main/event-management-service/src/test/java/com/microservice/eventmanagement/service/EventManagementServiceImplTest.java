package com.microservice.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ticketplaza.microservice.eventmanagement.dto.EventDto;
import com.ticketplaza.microservice.eventmanagement.entity.Event;
import com.ticketplaza.microservice.eventmanagement.repository.EventManagementRepository;
import com.ticketplaza.microservice.eventmanagement.service.EventManagementServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EventManagementServiceImplTest {
	
	@Mock
	EventManagementRepository eventManagementRepositiory;
	
	@InjectMocks
	EventManagementServiceImpl eventmanagementService;
	
	
	@Test
	void createEventTest_Success() {
		
		Event event=new Event();
		
		event.setId(1);
		event.setBookedSeats(25);
		event.setEventName("Gaurav's event");
		event.setOrganizerId("4635");
		event.setEventDescription("xyz");
		event.setLocation("pune");
		
		EventDto dto=new EventDto();
		
		dto.setId(1);
		dto.setBookedSeats(25);
		dto.setEventName("Gaurav's event");
		dto.setOrganizerId("4635");
		dto.setEventDescription("xyz");
		dto.setLocation("pune");
		
		when(eventManagementRepositiory.save(any())).thenReturn(event);

		
		EventDto savedEvent= eventmanagementService.createEvent(dto);
		
		assertNotNull(savedEvent);
		assertEquals(1,savedEvent.getId());
		assertEquals(25,savedEvent.getBookedSeats());
		assertEquals("Gaurav's event",savedEvent.getEventName());
		assertEquals("4635",savedEvent.getOrganizerId());
		assertEquals("xyz",savedEvent.getEventDescription());
		assertEquals("pune",savedEvent.getLocation());
		
		
	}

}
