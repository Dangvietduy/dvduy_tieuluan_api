package com.web.internship_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.internship_api.entities.Evaluate;

public interface EvaluteRepository extends JpaRepository<Evaluate, Integer>{
	Optional<Evaluate> findByIdAndDeleted(int id, byte deleted);
	@Query("SELECT i FROM Evaluate i WHERE i.internshipsStudent.id = :internshipsStudentId AND i.deleted = 0 ")
	Optional<Evaluate> findByInternshipStudentId(int internshipsStudentId);
}
