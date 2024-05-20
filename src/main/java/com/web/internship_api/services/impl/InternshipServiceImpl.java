package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Internship;
import com.web.internship_api.entities.Teacher;
import com.web.internship_api.models.InternShipModel;
import com.web.internship_api.models.SearchInternship;
import com.web.internship_api.repositories.InternshipRepository;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.TeacherService;

@Service
public class InternshipServiceImpl implements InternshipService{
	@Autowired
	InternshipRepository internshipRepository;
	@Autowired
	TeacherService teacherService;
	
	@Override
	public List<Internship> findAll() {
		return internshipRepository.findByDeleted((byte) 0);
	}
	
	@Override
	public List<Internship> seacheByName(String name) {
		return internshipRepository.seachInternshipByName(name);
	}

	
	@Override
	public Optional<Internship> findById(int id) {
		Optional<Internship> internship = internshipRepository.findByIdAndDeleted(id,(byte) 0);
		return internship;
	}

	@Override
	public Optional<Internship> finndByNameInternship(String name) {
		Optional<Internship> internship = internshipRepository.findByNameInternshipAndDeleted(name,(byte) 0);
		return internship;
	}

	@Override
	public Internship createInternShip(InternShipModel model) {
		Internship internship = this.setInternShipModel(model);
		if(internship != null )
			return internshipRepository.save(internship);
		return null;
	}

	@Override
	public Internship updateInternShip(InternShipModel model) {
		Internship internship = this.setInternShipModel(model);
		if(internship != null ) {
			internship.setId(model.getId());
			return internshipRepository.save(internship);
		}
		return null;
	}
	
	@Override
	public Internship deletedInternShip(int id) {
		Optional<Internship> internship = this.findById(id);
		if(internship.isPresent()) {
			internship.get().setDeleted((byte) 1);
			return internshipRepository.save(internship.get());
		}
		return null;
	}
	
	private Internship setInternShipModel(InternShipModel model) {
		Optional<Teacher> teacher = teacherService.findById(model.getTeacher_id());
		if(teacher.isPresent()) {
			Internship internship = new Internship();
			internship.setNameInternship(model.getNameInternShip());
			internship.setStartDay(model.getStartDay());
			internship.setEndDay(model.getEndDay());
			internship.setCourseInternship(model.getCourseInternShip());
			internship.setDescription(model.getDescription());
			internship.setAddress(model.getAddress());
			internship.setTeacher(teacher.get());
			return internship;
		}
		return null;
	}

	@Override
	public List<Internship> searchInternship(SearchInternship model) {
		List<Internship> list = internshipRepository.seachInternship(model.getNameInternship(),
				model.getCourseInternship(), model.getNameTeacher());
		return list;
	}

	@Override
	public List<Internship> searchInternshipNoCourse(SearchInternship model) {
		List<Internship> list = internshipRepository.seachInternshipNoCourse(model.getNameInternship(),model.getNameTeacher());
		return list;
	}
	
}
