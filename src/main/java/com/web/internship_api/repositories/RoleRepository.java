package com.web.internship_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.internship_api.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	 Optional<Role> findByName(String name);
}
