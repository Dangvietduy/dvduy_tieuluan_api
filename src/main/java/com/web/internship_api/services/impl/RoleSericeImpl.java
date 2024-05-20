package com.web.internship_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Role;
import com.web.internship_api.repositories.RoleRepository;
import com.web.internship_api.services.RoleService;

@Service
public class RoleSericeImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findById(int id) {
		return roleRepository.findById(id);
	}
	
	@Override
	public Optional<Role> findByName(String name) {
		return roleRepository.findByName(name);
	}

}
