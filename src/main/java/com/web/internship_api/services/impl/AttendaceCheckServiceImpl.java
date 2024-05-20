package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.AttendanceCheck;
import com.web.internship_api.repositories.AttendanceCheckRepository;
import com.web.internship_api.services.AttendanceCheckService;

@Service
public class AttendaceCheckServiceImpl implements AttendanceCheckService{
	@Autowired
	AttendanceCheckRepository attendanceCheckRepository;
	
	@Override
	public List<AttendanceCheck> findAll() {
		return attendanceCheckRepository.findAll();
	}
	
	@Override
	public Optional<AttendanceCheck> findById(int id) {
		return attendanceCheckRepository.findByIdAndDeleted(id, (byte) 0);
	}
	
	@Override
	public List<AttendanceCheck> findByInternshipsStudentId(int id) {
		return attendanceCheckRepository.findByInternshipsStudentIdAndDeleted(id,(byte) 0);
	}

	@Override
	public AttendanceCheck createAttendance(AttendanceCheck attendace) {
		return attendanceCheckRepository.save(attendace);
	}

	@Override
	public AttendanceCheck updateAttendance(AttendanceCheck attendace) {
		return attendanceCheckRepository.save(attendace);
	}

	@Override
	public AttendanceCheck deteleAttendance(int id) {
		Optional<AttendanceCheck> att = this.findById(id);
		if(att.isPresent()) {
			att.get().setDeleted((byte)1);
			return attendanceCheckRepository.save(att.get());
		}
		return null;
	}
}
