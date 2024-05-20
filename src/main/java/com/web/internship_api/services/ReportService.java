package com.web.internship_api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.web.internship_api.entities.Report;
import com.web.internship_api.models.ReportModel;

public interface ReportService {
	Stream<Report> findAll();
	Optional<Report> findById(int id);
	List<Report> findByStudentID(int studentId);
	List<Report> findByInternshipID(int internshipId);
	Report createReport(Report report);
	Report updateReport(ReportModel model);
	Report deletedReport(int id);
}
