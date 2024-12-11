package com.api.ticketsplaza.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.ticketsplaza.dto.UserDTO;
import com.api.ticketsplaza.exception.InvalidInputException;
import com.api.ticketsplaza.exception.TicketPlazaErrorCodes;
import com.api.ticketsplaza.model.LoginRequest;
import com.api.ticketsplaza.model.User;
import com.api.ticketsplaza.repository.UserDAO;
import com.api.ticketsplaza.utility.JWTUtil;
import com.api.ticketsplaza.utility.Response;
import com.api.ticketsplaza.utility.StringUtility;

@Service
public class UserService implements UserServiceInterface {

	private final UserDAO userDAO;
	private final JWTUtil jwtUtil;



	@Autowired
    public UserService(UserDAO userDAO, JWTUtil jwtUtil) {
        this.userDAO = userDAO;
		this.jwtUtil = jwtUtil;
    }
	
	private Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$");
	private Pattern passwordPattern = Pattern
			.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!*])(?!.*\\s).{8,}$");
	private Pattern namePattern = Pattern.compile("^[A-Za-z\\s]+$");
	private Pattern mobilePattern = Pattern.compile("^\\d{10}$");
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);

	@Override
	public void registerNewUser(UserDTO userDTO) {
		User user = new User();

		if (userDTO == null) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT, "Please provide valid input data");
		}

		if (StringUtility.isStringEmpty(userDTO.getEmail())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT,
					"Please provide email id and try again");
		}
		if (StringUtility.isStringEmpty(userDTO.getName())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT, "Please provide name and try again");
		}
		if (StringUtility.isStringEmpty(userDTO.getMobileNumber())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT,
					"Please provide mobile number and try again");
		}
		if (StringUtility.isStringEmpty(userDTO.getPassword())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT,
					"Please provide password and try again");
		}

		if (!isEmailValid(userDTO.getEmail())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_EMAIL_FORMAT,
					"Please enter a valid Email Address format and try again");
		}
		if (!isPasswordValid(userDTO.getPassword())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_PASSWORD_FORMAT,
					"Please provide a valid Password format, must contains at least one uppercase letter, one lowercase letter, one digit, one special character, no whitespace allowed, and a minimum length of 8 character");
		}
		if (!isMobileNumberValid(userDTO.getMobileNumber())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_MOBILE_NUMBER_FORMAT,
					"Please enter a valid Mobile Number and try again");
		}

		if (!isNameValid(userDTO.getName())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_NAME_FORMAT,
					"Please enter a valid Name and try again");
		}
		if (userDAO.existsByEmail(userDTO.getEmail())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.ALREADY_EXISTED_EMAIL,
					"The Email Id is already registered");
		}

		if (userDAO.existsByMobileNumber(userDTO.getMobileNumber())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.ALREADY_EXISTED_MOBILE_NUMBER,
					"The Mobile Number is already registered");
		}
		String hashPassword = passwordEncoder.encode(userDTO.getPassword());
		userDTO.setPassword(hashPassword);

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setMobileNumber(userDTO.getMobileNumber());

		userDAO.save(user);
	}

	private boolean isEmailValid(String email) {
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	private boolean isPasswordValid(String password) {
		Matcher matcher = passwordPattern.matcher(password);
		return matcher.matches();
	}

	private boolean isMobileNumberValid(String mobileNumber) {
		Matcher matcher = mobilePattern.matcher(mobileNumber);
		return matcher.matches();
	}

	private boolean isNameValid(String name) {
		Matcher matcher = namePattern.matcher(name);
		return matcher.matches();
	}

	@Override
	public ResponseEntity<Response> userLogin(LoginRequest loginRequest) {
		
		
		User user = userDAO.findByEmail(loginRequest.getEmail());
		
		if (StringUtility.isStringEmpty(loginRequest.getEmail())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT,
					"Please provide email id and try again");
		}
		if (StringUtility.isStringEmpty(loginRequest.getPassword())) {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_INPUT,
					"Please provide password and try again");
		}
		
		String enteredPassword = loginRequest.getPassword();
		if (user != null && passwordEncoder.matches(enteredPassword, user.getPassword())) {
			String jwtToken=jwtUtil.generateToken(user.getEmail());
			return ResponseEntity.ok(new Response("Success",jwtToken));
		} else {
			throw new InvalidInputException(TicketPlazaErrorCodes.INVALID_CREDENTIALS,
					"Please check your Email Id or Password, and try again");
		}

	}

}
