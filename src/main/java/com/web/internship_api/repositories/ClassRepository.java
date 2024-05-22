package com.web.internship_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassRepository extends JpaRepository<com.web.internship_api.entities.Class, Integer>{
	
	Optional<com.web.internship_api.entities.Class> findById(int id);
	
	
}
