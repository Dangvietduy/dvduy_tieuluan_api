package com.web.internship_api.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.internship_api.entities.Report;
import com.web.internship_api.models.ReportModel;
import com.web.internship_api.repositories.ReportRepository;
import com.web.internship_api.services.InternshipService;
import com.web.internship_api.services.ReportService;
import com.web.internship_api.services.StudentService;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	StudentService studentService;
	@Autowired
	InternshipService internshipService;
	@Autowired
	ReportRepository reportRepository;
	
	@Override
	public Stream<Report> findAll() {
		 return reportRepository.findAll().stream();
	}
	
	@Override
	public Optional<Report> findById(int id) {
		return reportRepository.findByIdAndDeleted(id, (byte) 0);
	}
	
	@Override
	public List<Report> findByStudentID(int studentId) {
		return reportRepository.findByStudentId(studentId);
	}

	@Override
	public List<Report> findByInternshipID(int internshipId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report createReport(Report report){
		return reportRepository.save(report);
	}

	@Override
	public Report updateReport(ReportModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report deletedReport(int id) {
		Optional<Report> report = this.findById(id);
		if(report.isPresent()) {
			reportRepository.delete(report.get());
			return report.get();
		}
		return null;
	}

}
