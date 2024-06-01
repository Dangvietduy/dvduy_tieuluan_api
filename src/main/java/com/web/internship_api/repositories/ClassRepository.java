package com.web.internship_api.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.Student;

public interface ClassRepository extends JpaRepository<com.web.internship_api.entities.Class, Integer>{

	Optional<com.web.internship_api.entities.Class> findById(int id);
	
	
	
	
	@Query("SELECT c FROM Class c WHERE c.id = :id "
			+ " AND c.name LIKE :name"
			+ " AND c.code LIKE :code"
			+ " AND c.major LIKE :major"
			+ " AND c.teacher LIKE :teacher"
			)
	List<com.web.internship_api.entities.Class> searchClass(@Param("id") int id, @Param("name") String name);
	
}
