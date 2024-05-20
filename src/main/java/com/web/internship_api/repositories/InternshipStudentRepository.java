package com.web.internship_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.internship_api.entities.InternshipsStudent;

public interface InternshipStudentRepository extends JpaRepository<InternshipsStudent, Integer>{
    Optional<InternshipsStudent> findByStudentId(int studentid);
}
