package com.web.internship_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.Major;

public interface MajorRepository extends JpaRepository<Major, Integer>{
	
	Optional<Major> findById(int id);
	
	@Query("SELECT m FROM Major m WHERE m.id = :id AND m.name LIKE :name")
//    Optional<Major> findByIdAndNameLike(@Param("id") int id, @Param("name") String name);
//	@Query("SELECT m FROM major m WHERE m.id = :id AND m.name LIKE %:name%")
	List<Major> searchMajor(@Param("id") int id, @Param("name") String name);
}
