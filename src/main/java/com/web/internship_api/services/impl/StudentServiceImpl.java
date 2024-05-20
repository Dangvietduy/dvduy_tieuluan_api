package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.Student;
import com.web.internship_api.models.SearchStatisticial;
import com.web.internship_api.models.SearchStudentModel;
import com.web.internship_api.models.StudentModel;
import com.web.internship_api.models.UltilSetModel;
import com.web.internship_api.repositories.StudentRepository;
import com.web.internship_api.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Student> findAll() {
		return studentRepository.findByDeleted((byte) 0);
	}

	@Override
	public Optional<Student> findById(int id) {
		return studentRepository.findByIdAndDeleted(id,(byte) 0);
	}

	@Override
	public Optional<Student> findByName(String name) {
		return studentRepository.findByNameAndDeleted(name,(byte) 0);
	}

	@Override
	public Student createStudent(StudentModel model, Account account) {
		Student student = UltilSetModel.setStudent(model);
		student.setAccount(account);
		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(StudentModel model,int id) {
		Optional<Student> optional = this.findById(id);
		if(optional.isPresent()) {
			Student student = UltilSetModel.setStudent(model);
			student.setId(optional.get().getId());
			student.setAccount(optional.get().getAccount());
			return studentRepository.save(student);
		}
		return null;
	}

	@Override
	public Student deltedStudent(int id) {
		Optional<Student> optional = this.findById(id);
		if(optional.isPresent()) {
			Student student = optional.get();
			student.getAccount().setDeleted((byte) 1);
			student.setDeleted((byte) 1);
			return studentRepository.save(student);
		}
		return null;
	}

	@Override
	public List<Student> searchStudent(SearchStudentModel model) {
		return studentRepository.searchStudent(model.getName(), model.getClassName(), model.getYear_study(),"");
	}

	@Override
	public List<Student> statisticalStudent(SearchStatisticial model) {
		List<Student> listStudent = null;
		if(model.getCourse() == 0 && model.getInternshipName() == "" && model.getNameTeacher() =="") {
			listStudent = studentRepository.searchStudent(
					model.getNameStudent(), 
					model.getClassName(),
					model.getYear_study(),
					model.getSex());
		}else {
			if(model.getCourse() == 0) {
				listStudent = studentRepository.statisticalStudentNoCourse(
						model.getNameStudent(), 
						model.getClassName(), 
						model.getYear_study(),
						model.getSex(), 
						model.getNameTeacher(), 
						model.getInternshipName());
			}else {
				listStudent = studentRepository.statisticalStudent(
						model.getNameStudent(), 
						model.getClassName(), 
						model.getYear_study(),
						model.getSex(), 
						model.getNameTeacher(), 
						model.getInternshipName(),
						model.getCourse());
			}
		}
		return listStudent;
	}
	
}
