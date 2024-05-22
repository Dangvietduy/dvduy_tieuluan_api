package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Company;
import com.web.internship_api.entities.Major;
import com.web.internship_api.models.MajorModel;
import com.web.internship_api.repositories.MajorRepository;
import com.web.internship_api.services.MajorService;

@Service
public class MajorServiceImpl implements MajorService{

	@Autowired
	MajorRepository majorRepository;
	
	@Override
	public List<Major> findAll() {
		// TODO Auto-generated method stub
		return majorRepository.findAll();
	}

	@Override
	public Optional<Major> findById(int id) {
		// TODO Auto-generated method stub
		return majorRepository.findById(id);
	}

	@Override
	public List<Major> searchMajor(MajorModel model) {
		List<Major> majors = majorRepository.searchMajor(model.getId(), model.getName());
		return majors;
	}

	@Override
	public Major createMajor(MajorModel model) {
		Optional<Major> optional = this.findById(model.getId());
		if(optional.isEmpty()) {
			Major major = new Major();
			major.setId(model.getId());
			major.setName(model.getName());
			return majorRepository.save(major);
		}
		return null;
	}

	@Override
	public Major updateMajor(MajorModel model) {
		Optional<Major> optional = this.findById(model.getId());
		if(optional.isPresent()) {
			Major major = optional.get();
			major.setId(model.getId());
			major.setName(model.getName());			
			return majorRepository.save(major);
		}
		return null;
	}

	@Override
	public Major deleteMajor(int id) {
		Optional<Major> major = majorRepository.findById(id);
		if(major.isPresent()) {
			majorRepository.delete(major.get());
			return major.get();
		}
		return null;
	}

}
