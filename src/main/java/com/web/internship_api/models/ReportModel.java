package com.web.internship_api.models;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ReportModel {
	private int studentId;
	private int internshipId;
	private Date reportDay;
	private String reportContent;
	private MultipartFile fileReport;
	public ReportModel() {
		
	}
	public ReportModel(int studentId, int internshipId, Date reportDay, String reportContent,
			MultipartFile fileReport) {
		super();
		this.studentId = studentId;
		this.internshipId = internshipId;
		this.reportDay = reportDay;
		this.reportContent = reportContent;
		this.fileReport = fileReport;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getInternshipId() {
		return internshipId;
	}
	public void setInternshipId(int internshipId) {
		this.internshipId = internshipId;
	}
	public Date getReportDay() {
		return reportDay;
	}
	public void setReportDay(Date reportDay) {
		this.reportDay = reportDay;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public MultipartFile getFileReport() {
		return fileReport;
	}
	public void setFileReport(MultipartFile fileReport) {
		this.fileReport = fileReport;
	}
	
	
}
