package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.SearchTeacherModel;
import com.web.internship_api.models.TeacherModel;


public interface TeacherService {
	List<Teacher> findAll();
	Optional<Teacher> findById(int id);
	Optional<Teacher> findByName(String name);
	List<Teacher> searchTeacher(SearchTeacherModel model);
	Teacher createTeacher(TeacherModel model, Account account);
	Teacher updateTeacher(TeacherModel model,int id);
	Teacher deleteTeacher(int id);
}
