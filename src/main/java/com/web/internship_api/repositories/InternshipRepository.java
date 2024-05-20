package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Integer>{
	List<Internship> findByDeleted(byte deleted);
	Optional<Internship> findByIdAndDeleted(int id, byte deleted);
	Optional<Internship> findByNameInternshipAndDeleted(String name, byte deleted);
	@Query("SELECT e FROM Internship e WHERE e.nameInternship LIKE %:name%")
	List<Internship> seachInternshipByName(@Param("name") String name);
	@Query("SELECT e FROM Internship e WHERE e.nameInternship LIKE %:nameInternship% AND e.courseInternship =:courseInternship AND e.teacher.name LIKE %:nameTeacher% AND e.deleted = 0")
	List<Internship> seachInternship(@Param("nameInternship") String nameInternship,@Param("courseInternship") int courseInternship,@Param("nameTeacher") String nameTeacher);
	@Query("SELECT e FROM Internship e WHERE e.nameInternship LIKE %:nameInternship% AND e.teacher.name LIKE %:nameTeacher% AND e.deleted = 0")
	List<Internship> seachInternshipNoCourse(@Param("nameInternship") String nameInternship,@Param("nameTeacher") String nameTeacher);
}
