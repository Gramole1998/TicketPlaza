package com.api.ticketsplaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ticketsplaza.model.Role;

public interface RoleDao extends JpaRepository<Role, Long > {
	

}
