package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.Student;
import com.web.internship_api.models.SearchStatisticial;
import com.web.internship_api.models.SearchStudentModel;
import com.web.internship_api.models.StudentModel;

public interface StudentService {
	List<Student> findAll();
	Optional<Student> findById(int id);
	Optional<Student> findByName(String name);
	List<Student> searchStudent(SearchStudentModel model);
	List<Student> statisticalStudent(SearchStatisticial model);
	Student createStudent(StudentModel model, Account account);
	Student updateStudent(StudentModel model, int id);
	Student deltedStudent(int id);
}
