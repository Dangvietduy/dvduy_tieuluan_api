package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Major;
import com.web.internship_api.models.MajorModel;

public interface MajorService {

	List<Major> findAll();
	Optional<Major> findById(int id);
	List<Major> searchMajor(MajorModel model);
	Major createMajor(MajorModel model);
	Major updateMajor(MajorModel model);
	Major deleteMajor(int id);
}
