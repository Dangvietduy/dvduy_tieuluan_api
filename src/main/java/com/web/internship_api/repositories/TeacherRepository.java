package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	List<Teacher> findByDeleted(byte deleted);
	Optional<Teacher> findByIdAndDeleted(int id, byte deleted);
	Optional<Teacher> findByNameAndDeleted(String name,byte deleted);
	@Query("SELECT t FROM Teacher t WHERE t.name LIKE %:name% AND t.level LIKE %:level% AND t.specialize LIKE %:specialize% AND t.deleted = 0")
	List<Teacher> searchTeacher(@Param("name") String name,@Param("level") String level,@Param("specialize") String specialize);
}
