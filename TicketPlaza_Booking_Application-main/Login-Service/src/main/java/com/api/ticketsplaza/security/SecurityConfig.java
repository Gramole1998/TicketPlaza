package com.api.ticketsplaza.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.ticketsplaza.service.MyUserDetailService;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	MyUserDetailService userDetailService;
	@Autowired
	private JWTFilter jwtFilter;
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers("/api/user/generate-token").authenticated() // Require authentication for this
            .anyRequest().permitAll()
        ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .httpBasic(Customizer.withDefaults());

    return http.build();
}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setPasswordEncoder(new BCryptPasswordEncoder(10));
		dao.setUserDetailsService(userDetailService);
		return dao;
	}
	

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		 return new BCryptPasswordEncoder();
//	}
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		
		return configuration.getAuthenticationManager();
		
	}
}
