package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.AttendanceCheck;

public interface AttendanceCheckService {
	List<AttendanceCheck> findAll();
	Optional<AttendanceCheck> findById(int id);
	List<AttendanceCheck> findByInternshipsStudentId(int id);
	AttendanceCheck createAttendance(AttendanceCheck attendace);
	AttendanceCheck updateAttendance(AttendanceCheck attendace);
	AttendanceCheck deteleAttendance(int id);
}
