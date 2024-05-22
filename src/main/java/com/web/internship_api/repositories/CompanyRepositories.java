package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Teacher;

public interface CompanyRepositories extends JpaRepository<Company, Integer>{
	
	Optional<Company> findById(int id);
	
//	@Query("SELECT i FROM companies i WHERE i.id = :id AND i.deleted = 0 ")
//	Optional<Company> findByInternshipStudentId(int internshipsStudentId);
	
	@Query("SELECT c FROM Company c WHERE c.id = :id AND c.name LIKE :name")
	List<Company> searchCompany(@Param("id") int id,@Param("name") String name);
}
