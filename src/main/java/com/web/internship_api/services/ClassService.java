package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Class;
import com.web.internship_api.models.ClassModel;

public interface ClassService {
	List<Class> findAll();
	Optional<Class> findByClassId(int id);
	List<Class> searchClass(ClassModel model);
	Class createClass(ClassModel model);
	Class updateClass(ClassModel model);
	Class deleteClass(int id);
}
