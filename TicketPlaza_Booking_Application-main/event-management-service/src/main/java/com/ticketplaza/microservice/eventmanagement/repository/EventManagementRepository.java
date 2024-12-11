package com.ticketplaza.microservice.eventmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketplaza.microservice.eventmanagement.entity.Event;

public interface EventManagementRepository extends JpaRepository<Event, Long> {

	
}
