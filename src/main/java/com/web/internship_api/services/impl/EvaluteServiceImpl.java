package com.web.internship_api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Evaluate;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.models.EvaluateModel;
import com.web.internship_api.repositories.EvaluteRepository;
import com.web.internship_api.services.EvaluteService;
import com.web.internship_api.services.InternshipStudentService;

@Service
public class EvaluteServiceImpl implements EvaluteService{
	@Autowired
	EvaluteRepository evaluteRepository;
	@Autowired
	InternshipStudentService internshipStudentService;
	
	@Override
	public Optional<Evaluate> findByInternshipStudentId(int internshipStudentId) {
		return evaluteRepository.findByInternshipStudentId(internshipStudentId);
	}
	
	@Override
	public Evaluate createEvalute(EvaluateModel model) {
		Optional<Evaluate> optional = this.findByInternshipStudentId(model.getInternshipsStudentId());
		if(optional.isEmpty()) {
			Evaluate evaluate = this.setModel(model);
			return evaluteRepository.save(evaluate);
		}
		return null;
	}

	@Override
	public Evaluate updateEvalute(EvaluateModel model) {
		Optional<Evaluate> optional = this.findByInternshipStudentId(model.getInternshipsStudentId());
		if(optional.isPresent()) {
			Evaluate evaluate = this.setModel(model);
			evaluate.setId(model.getId());
			return evaluteRepository.save(evaluate);
		}
		return null;
	}

	@Override
	public Evaluate deleteEvalute(int id) {
		Optional<Evaluate> evaluate = evaluteRepository.findByIdAndDeleted(id, (byte)0);
		if(evaluate.isPresent()) {
			evaluate.get().setDeleted((byte) 1);
			return evaluteRepository.save(evaluate.get());
		}
		return null;
	}
	
	private Evaluate setModel(EvaluateModel model ) {
		Optional<InternshipsStudent> internshipStudent = internshipStudentService.findById(model.getInternshipsStudentId());
		if(internshipStudent.isPresent()) {
			Evaluate evaluate =  new Evaluate();
			evaluate.setInternshipsStudent(internshipStudent.get());
			evaluate.setScore(model.getScore());
			evaluate.setEvaluateDay(model.getEvaluateDay());
			evaluate.setEvaluateContent(model.getEvaluateContent());
			return evaluate;
		}
		return null;
	}

	
}
