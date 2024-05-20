package com.web.internship_api.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.internship_api.entities.AttendanceCheck;

public interface AttendanceCheckRepository extends JpaRepository<AttendanceCheck, Integer>{
	Optional<AttendanceCheck> findByIdAndDeleted(int id, byte deleted);
	List<AttendanceCheck> findByInternshipsStudentIdAndDeleted(int id,byte deleted);
	List<AttendanceCheck> findByAttendanceDayCheckAndDeleted(Date date, byte deleted);
	@Query("SELECT e FROM AttendanceCheck e WHERE e.attendanceDayCheck  BETWEEN :startDate AND :endDate AND e.deleted = 0")
	List<AttendanceCheck> findByAttendanceDayCheckFromtoDay(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
