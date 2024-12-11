package com.api.ticketsplaza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.ticketsplaza.model.User;
import com.api.ticketsplaza.model.UserPrinciple;
import com.api.ticketsplaza.repository.UserDAO;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	UserDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=dao.findByName(username);
		if(user==null) {
			throw new UsernameNotFoundException("User Not Found");
			
		}
		else {
			return new UserPrinciple(user);
		}
		
		
	}

}
