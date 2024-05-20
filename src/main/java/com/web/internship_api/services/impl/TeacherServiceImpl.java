package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Account;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.SearchTeacherModel;
import com.web.internship_api.models.TeacherModel;
import com.web.internship_api.models.UltilSetModel;
import com.web.internship_api.repositories.TeacherRepository;
import com.web.internship_api.services.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	TeacherRepository teacherRepository;
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findByDeleted((byte) 0);
	}

	@Override
	public Optional<Teacher> findById(int id) {
		return teacherRepository.findByIdAndDeleted(id, (byte) 0);
	}

	@Override
	public Optional<Teacher> findByName(String name) {
		return teacherRepository.findByNameAndDeleted(name, (byte) 0);
	}

	@Override
	public List<Teacher> searchTeacher(SearchTeacherModel model) {
		List<Teacher> teachers = teacherRepository.searchTeacher(model.getName(), model.getLevel(), model.getSpecialize());
		return teachers;
	}
	
	@Override
	public Teacher createTeacher(TeacherModel model, Account account) {
		Teacher teacher = UltilSetModel.setTeacher(model);
		teacher.setAccount(account);
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher updateTeacher(TeacherModel model, int id) {
		Optional<Teacher> optional = teacherRepository.findById(id);
		if(optional.isPresent()) {
			Teacher teacher = UltilSetModel.setTeacher(model);
			teacher.setId(optional.get().getId());
			teacher.setAccount(optional.get().getAccount());
			return teacherRepository.save(teacher);
		}
		return null;
	}

	@Override
	public Teacher deleteTeacher(int id) {
		Optional<Teacher> teacher = findById(id);
		if(teacher.isPresent()) {
			Teacher teach = teacher.get();
			teach.getAccount().setDeleted((byte) 1);
			teach.setDeleted((byte)1);
			return teacherRepository.save(teach);
		}
		return null;
	}

	
}
