package com.ticketplaza.microservice.booking.exceptioHandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class Errordetail {

	private LocalDateTime time;
	private String errorMessage;
	private HttpStatus status;
}
