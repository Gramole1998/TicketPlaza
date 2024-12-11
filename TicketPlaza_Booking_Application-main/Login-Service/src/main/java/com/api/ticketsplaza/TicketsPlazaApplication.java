package com.api.ticketsplaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.api.ticketsplaza")
public class TicketsPlazaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsPlazaApplication.class, args);
	}

}
