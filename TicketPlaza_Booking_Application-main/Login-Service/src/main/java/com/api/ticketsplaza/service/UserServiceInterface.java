package com.api.ticketsplaza.service;

import org.springframework.http.ResponseEntity;

import com.api.ticketsplaza.dto.UserDTO;
import com.api.ticketsplaza.model.LoginRequest;
import com.api.ticketsplaza.utility.Response;

public interface UserServiceInterface {

	public void registerNewUser(UserDTO userDTO);

	public ResponseEntity<Response> userLogin(LoginRequest loginRequest);
}
