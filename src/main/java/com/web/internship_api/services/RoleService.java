package com.web.internship_api.services;

import java.util.Optional;

import com.web.internship_api.entities.Role;

public interface RoleService {
	Optional<Role> findById(int id);
	Optional<Role> findByName(String name);
}
