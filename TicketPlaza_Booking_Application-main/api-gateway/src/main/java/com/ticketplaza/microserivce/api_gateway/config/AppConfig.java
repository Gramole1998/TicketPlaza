package com.ticketplaza.microserivce.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ticketplaza.microserivce.api_gateway.filter.AuthenticationFilter;
import com.ticketplaza.microserivce.api_gateway.util.JWTUtil;

//@Configuration
public class AppConfig {
	
	@Bean
	public AuthenticationFilter augthenticationFilter() {
		return new AuthenticationFilter();
	}
//	
	@Bean
	public JWTUtil jwtUtil() {
		return new JWTUtil();
	}

}
