package com.api.ticketsplaza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.ticketsplaza.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
	boolean existsByMobileNumber(String mobileNumber);
	User findByEmail(String email);
	User findByName(String userName);
}
