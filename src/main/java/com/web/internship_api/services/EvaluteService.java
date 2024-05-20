package com.web.internship_api.services;

import java.util.Optional;

import com.web.internship_api.entities.Evaluate;
import com.web.internship_api.models.EvaluateModel;

public interface EvaluteService {
	Optional<Evaluate> findByInternshipStudentId(int internshipStudentId);
	Evaluate createEvalute(EvaluateModel model);
	Evaluate updateEvalute(EvaluateModel model);
	Evaluate deleteEvalute(int id);
}
