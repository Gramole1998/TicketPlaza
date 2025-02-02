package com.ticketplaza.microservice.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ticketplaza.microservice.eventmanagement.dto.EventDto;
import com.ticketplaza.microservice.eventmanagement.service.EventManagementService;

@RestController
@RequestMapping("/api/eventmanagement")
public class EventManagementController {
	
	
	private final EventManagementService eventManagementService;
	
	@Autowired
	public EventManagementController(EventManagementService eventManagementService) {
	 this.eventManagementService=eventManagementService;
	}
	
	@PostMapping("/createEvent")
	public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
		EventDto createdEvent= eventManagementService.createEvent(eventDto);
		return new ResponseEntity<EventDto>(createdEvent, HttpStatus.CREATED);
	}
	
	@DeleteMapping("deleteEvent/{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable long id){
		eventManagementService.deleteEvent(id);
		return new ResponseEntity<String>("The Event got deleted successfully",HttpStatus.OK);
		
	}
	
	@GetMapping("/eventDetails/{id}")
	public ResponseEntity<EventDto> getEventDetails(@PathVariable long id){
		EventDto event=eventManagementService.getEventDetails(id);
		return new ResponseEntity<EventDto>(event,HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity<String> create() {
		return new ResponseEntity<String>("createdEvent", HttpStatus.CREATED);
	}
	
	@PutMapping("/updateEventDetails/{id}")
	public ResponseEntity<EventDto> updateEventDetails(@PathVariable long id,@RequestBody EventDto eventDto){
		EventDto updatedEvent = eventManagementService.updateEventDetails(id,eventDto);
		return new ResponseEntity<EventDto>(updatedEvent,HttpStatus.OK);
	}

}
