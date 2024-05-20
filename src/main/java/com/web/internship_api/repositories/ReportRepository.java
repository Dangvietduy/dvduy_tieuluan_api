package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.internship_api.entities.Report;

public interface ReportRepository extends JpaRepository<Report, Integer>{
	List<Report> findByDeleted(byte deleted);
	Optional<Report> findByIdAndDeleted(int id, byte Deleted);
	@Query("SELECT i FROM Report i WHERE i.student.id = :studentid")
    List<Report> findByStudentId(int studentid);
	@Query("SELECT i FROM Report i WHERE i.internship.id = :internshipid")
    List<Report> findByInternshipId(int internshipid);
}
