package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte deleted;

	@Lob
	@Column(name="file_report")
	private byte[] fileReport;

	@Column(name="report_content")
	private String reportContent;

	@Temporal(TemporalType.DATE)
	@Column(name="report_day")
	private Date reportDay;

	//bi-directional many-to-one association to Internship
	@ManyToOne
	private Internship internship;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public Report() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public byte[] getFileReport() {
		return this.fileReport;
	}

	public void setFileReport(byte[] fileReport) {
		this.fileReport = fileReport;
	}

	public String getReportContent() {
		return this.reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	public Date getReportDay() {
		return this.reportDay;
	}

	public void setReportDay(Date reportDay) {
		this.reportDay = reportDay;
	}

	public Internship getInternship() {
		return this.internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}