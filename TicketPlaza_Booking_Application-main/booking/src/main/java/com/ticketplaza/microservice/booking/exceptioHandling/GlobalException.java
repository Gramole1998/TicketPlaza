package com.ticketplaza.microservice.booking.exceptioHandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(TickedBookedException.class)
	public ResponseEntity<Errordetail> handleAllSeatBooked(TickedBookedException ex,WebRequest request){
		Errordetail detail=new Errordetail(LocalDateTime.now(), ex.getMessage(), ex.getStatus());
		return new ResponseEntity<>(detail,HttpStatus.BAD_REQUEST);
		
	}

}
