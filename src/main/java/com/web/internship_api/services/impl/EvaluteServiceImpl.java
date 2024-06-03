package com.web.internship_api.services.impl;

import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Evaluate;
import com.web.internship_api.entities.InternshipsStudent;
import com.web.internship_api.models.EvaluateModel;
import com.web.internship_api.repositories.EvaluteRepository;
import com.web.internship_api.services.EmailService;
import com.web.internship_api.services.EvaluteService;
import com.web.internship_api.services.InternshipStudentService;

@Service
public class EvaluteServiceImpl implements EvaluteService{
	@Autowired
	EvaluteRepository evaluteRepository;
	@Autowired
	InternshipStudentService internshipStudentService;
	@Autowired
	EmailService emailService;
	@Override
	public Optional<Evaluate> findByInternshipStudentId(int internshipStudentId) {
		return evaluteRepository.findByInternshipStudentId(internshipStudentId);
	}
	
	@Override
	public Evaluate createEvalute(EvaluateModel model) {
		Optional<Evaluate> optional = this.findByInternshipStudentId(model.getInternshipsStudentId());
		if(optional.isEmpty()) {
			Evaluate evaluate = this.setModel(model);
			Evaluate eval = evaluteRepository.save(evaluate);
			
			if(eval != null) {
				String subject = "Kết Quả Thực Tập";
				String body = "Điểm Của M: "+model.getScore()+" Lời Phê Của Tao: "+model.getEvaluateContent() ;
				try {
					emailService.sendMail(optional.get().getInternshipsStudent().getStudent().getEmail(), subject , body);
				} catch (MessagingException e) {
					e.getMessage();
				}
			}
			return eval;
		}
		return null;
	}

	@Override
	public Evaluate updateEvalute(EvaluateModel model) {
		Optional<Evaluate> optional = this.findByInternshipStudentId(model.getInternshipsStudentId());
		if(optional.isPresent()) {
			Evaluate evaluate = this.setModel(model);
			evaluate.setId(model.getId());
			Evaluate eval = evaluteRepository.save(evaluate);
			if(eval != null) {
				String subject = "Kết Quả Thực Tập";
				String body = "Điểm Của M: "+model.getScore()+" Lời Phê Của Tao: "+model.getEvaluateContent() ;
				try {
					emailService.sendMail(optional.get().getInternshipsStudent().getStudent().getEmail(), subject , body);
				} catch (MessagingException e) {
					e.getMessage();
				}
			}
			return eval;
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
