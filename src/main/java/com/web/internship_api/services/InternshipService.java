package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;

import com.web.internship_api.entities.Internship;
import com.web.internship_api.models.InternShipModel;
import com.web.internship_api.models.SearchInternship;

public interface InternshipService {
	List<Internship> findAll();
	List<Internship> seacheByName(String name);
	Optional<Internship> findById(int id);
	Optional<Internship> finndByNameInternship(String name);
	List<Internship> searchInternship(SearchInternship model);
	List<Internship> searchInternshipNoCourse(SearchInternship model);
	Internship createInternShip(InternShipModel model);
	Internship updateInternShip(InternShipModel model);
	Internship deletedInternShip(int id);
}
