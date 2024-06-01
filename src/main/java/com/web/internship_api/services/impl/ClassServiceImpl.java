package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Class;
import com.web.internship_api.entities.Company;
import com.web.internship_api.models.ClassModel;
import com.web.internship_api.repositories.ClassRepository;
import com.web.internship_api.services.ClassService;

@Service
public class ClassServiceImpl implements ClassService{
	@Autowired
	ClassRepository classRepository;

	@Override
	public List<Class> findAll() {
		return classRepository.findAll();
	}

	@Override
	public Optional<Class> findByClassId(int id) {
		// TODO Auto-generated method stub
		return classRepository.findById(id);
	}

	@Override
	public List<Class> searchClass(ClassModel model) {
		List<Class> classes = classRepository.searchClass(model.getId(), model.getName());
		return classes;
	}

	@Override
	public Class createClass(ClassModel model) {
		Optional<Class> optional = this.findByClassId(model.getId());
		if(optional.isEmpty()) {
			Class classes = new Class();
			classes.setName(model.getName());
			classes.setCode(model.getCode());
			classes.setMajor(model.getMajorId());
			classes.setTeacher(model.getTeacherId());
			return classRepository.save(classes);
		}
		return null;
	}

	@Override
	public Class updateClass(ClassModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class deleteClass(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
