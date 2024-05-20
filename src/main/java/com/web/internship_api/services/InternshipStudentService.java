package com.web.internship_api.services;

import java.util.Optional;

import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.models.InternshipStudentModel;

public interface InternshipStudentService {
	Optional<InternshipsStudent> findById(int id);
	InternshipsStudent createInternShip(InternshipStudentModel model);
	InternshipsStudent updateInternShip(InternshipStudentModel model );
	InternshipsStudent deletedInternShip(int id);
}
