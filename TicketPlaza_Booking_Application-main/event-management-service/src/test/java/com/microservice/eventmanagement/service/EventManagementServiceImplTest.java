package com.microservice.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.ticketplaza.microservice.eventmanagement.dto.EventDto;
import com.ticketplaza.microservice.eventmanagement.entity.Event;
import com.ticketplaza.microservice.eventmanagement.exception.EventManagementException;
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
	
	@Test
	void eventIdExceptionTest() {
		Event ev= new Event();
		ev.setId(5);
		
		when(eventManagementRepositiory.findById(5L)).thenThrow(new EventManagementException("invalid id",HttpStatus.BAD_REQUEST));
		
		EventManagementException exception = assertThrows(EventManagementException.class,
				()->eventmanagementService.getEventDetails(ev.getId()));
		
		assertEquals("invalid id", exception.getMessage());
	}
	@Test
	void getEventDetailTest() {
		Event actual_ev=new Event();
		actual_ev.setId(1);
		actual_ev.setBookedSeats(25);
		actual_ev.setEventName("Gaurav's event");
		actual_ev.setOrganizerId("4635");
		actual_ev.setEventDescription("xyz");
		actual_ev.setLocation("pune");
		
		when(eventManagementRepositiory.findById(any())).thenReturn(Optional.of(actual_ev));
		
		EventDto ev=eventmanagementService.getEventDetails(1);
		
		assertEquals(1, ev.getId());
		assertEquals(25, ev.getBookedSeats());
		assertEquals("Gaurav's event", ev.getEventName());
		assertEquals("4635", ev.getOrganizerId());
		assertEquals("xyz", ev.getEventDescription());
		assertEquals("pune", ev.getLocation());
	}
	@Test
	void updateEventDetails() {
		Event existing_event=new Event();
		existing_event.setId(1);
		existing_event.setBookedSeats(25);
		existing_event.setEventName("Gaurav's event");
		existing_event.setOrganizerId("4635");
		existing_event.setEventDescription("xyz");
		existing_event.setLocation("pune");
		
		Event updated_event=new Event();
		updated_event.setId(1);
		updated_event.setBookedSeats(26);
		updated_event.setEventName("Gaurav's event");
		updated_event.setOrganizerId("4635");
		updated_event.setEventDescription("xyz");
		updated_event.setLocation("pune");
		
		
		EventDto passed_dto=new EventDto();
		passed_dto.setId(1);
		passed_dto.setBookedSeats(25);
		passed_dto.setEventName("Gaurav's event");
		passed_dto.setOrganizerId("4635");
		passed_dto.setEventDescription("xyz");
		passed_dto.setLocation("pune");
		
		when(eventManagementRepositiory.findById(any())).thenReturn(Optional.of(existing_event));
		when(eventManagementRepositiory.save(any())).thenReturn(updated_event);
		
		EventDto result =eventmanagementService.updateEventDetails(1, passed_dto);
		
		assertEquals(1, result.getId());
		assertEquals(26, result.getBookedSeats());
		assertEquals("Gaurav's event", result.getEventName());
		assertEquals("4635", result.getOrganizerId());
		assertEquals("xyz", result.getEventDescription());
		assertEquals("pune", result.getLocation());
		
	}
	

}
