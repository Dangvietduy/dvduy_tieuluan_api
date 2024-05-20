package com.web.internship_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.entities.Student;
import com.web.internship_api.models.InternshipStudentModel;
import com.web.internship_api.repositories.InternshipStudentRepository;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.InternshipStudentService;
import com.web.internship_api.services.StudentService;

@Service
public class InternshipStudentServiceImpl implements InternshipStudentService{
	@Autowired 
	InternshipStudentRepository internshipStudentRepository;
	@Autowired
	StudentService studentService;
	@Autowired
	InternshipService internshipService;
	
	
	@Override
	public Optional<InternshipsStudent> findById(int id) {
		return internshipStudentRepository.findById(id);
	}

	@Override
	public InternshipsStudent createInternShip(InternshipStudentModel model) {
		Optional<InternshipsStudent> internshipStudent = internshipStudentRepository.findByStudentId(model.getStudentId());
		if(internshipStudent.isEmpty()) {
			Optional<Student> student = studentService.findById(model.getStudentId());
			Optional<Internship> intern = internshipService.findById(model.getInternshipId());
			if(student.isPresent() && intern.isPresent()) {
				InternshipsStudent internStudent = new InternshipsStudent();
				internStudent.setStudent(student.get());
				internStudent.setInternship(intern.get());
				return internshipStudentRepository.save(internStudent);
			}
		}
		return null;
	}

	@Override
	public InternshipsStudent updateInternShip(InternshipStudentModel model) {
		Optional<InternshipsStudent> internshipStudent = internshipStudentRepository.findByStudentId(model.getStudentId());
		if(internshipStudent.isPresent()) {
			Optional<Student> student = studentService.findById(model.getStudentId());
			Optional<Internship> intern = internshipService.findById(model.getInternshipId());
			if(student.isPresent() && intern.isPresent()) {
				InternshipsStudent internStudent = new InternshipsStudent();
				internStudent.setId(internshipStudent.get().getId());
				internStudent.setAttendanceChecks(internshipStudent.get().getAttendanceChecks());
				internStudent.setEvaluates(internshipStudent.get().getEvaluates());
				internStudent.setStudent(student.get());
				internStudent.setInternship(intern.get());
				return internshipStudentRepository.save(internStudent);
			}
		}
		return null;
	}

	@Override
	public InternshipsStudent deletedInternShip(int id) {
		Optional<InternshipsStudent> internStudent = this.findById(id);
		if(internStudent.isPresent()) {
			internshipStudentRepository.deleteById(id);
			return internStudent.get();		
		}
		return null;
	}


}
