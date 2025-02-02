package com.ticketplaza.microservice.booking.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ticketplaza.microservice.booking.dto.EventDto;


@FeignClient(name="EVENT-SERVICE")
public interface APIClient {

	@GetMapping("/api/eventmanagement/eventDetails/{id}")
	EventDto getEventDetails(@PathVariable long id);
	
	@PutMapping("/api/eventmanagement/updateEventDetails/{id}")
    void updateEventDetails(@PathVariable long id,@RequestBody EventDto eventDto);
}
