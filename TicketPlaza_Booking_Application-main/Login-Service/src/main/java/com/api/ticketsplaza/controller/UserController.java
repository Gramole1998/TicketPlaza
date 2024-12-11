package com.api.ticketsplaza.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.ticketsplaza.dto.UserDTO;
import com.api.ticketsplaza.model.LoginRequest;
import com.api.ticketsplaza.service.UserServiceInterface;
import com.api.ticketsplaza.utility.JWTUtil;
import com.api.ticketsplaza.utility.Response;

@RestController
@RequestMapping("/api/user")
public class UserController {

	
	
	private final UserServiceInterface userService;
	private final JWTUtil jwtUtil;
	
	@Autowired
	public UserController(UserServiceInterface userService,JWTUtil jwtUtil) {
		super();
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
		
		userService.registerNewUser(userDTO);
		return ResponseEntity.ok("The user has registered successfully");

	}
	@PostMapping("/login")
	public ResponseEntity<Response> userLogin(@RequestBody LoginRequest loginRequest) {
		
		return userService.userLogin(loginRequest);

	}
	@PostMapping("/generate-token")
	public ResponseEntity<String> generateToken(Principal principal) {
		
		String token= jwtUtil.generateToken(principal.getName());
		return ResponseEntity.ok(token);

	}
	

}
