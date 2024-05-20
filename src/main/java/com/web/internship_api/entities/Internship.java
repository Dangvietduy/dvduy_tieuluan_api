package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the internship database table.
 * 
 */
@Entity
@NamedQuery(name="Internship.findAll", query="SELECT i FROM Internship i")
public class Internship implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Column(name="course_internship")
	private int courseInternship;

	private byte deleted;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="end_day")
	private Date endDay;

	@Column(name="name_internship")
	private String nameInternship;

	@Temporal(TemporalType.DATE)
	@Column(name="start_day")
	private Date startDay;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="companies_id")
	private Company company;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	private Teacher teacher;

	//bi-directional many-to-one association to InternshipsStudent
	@OneToMany(mappedBy="internship")
	private List<InternshipsStudent> internshipsStudents;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="internship")
	private List<Report> reports;

	public Internship() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCourseInternship() {
		return this.courseInternship;
	}

	public void setCourseInternship(int courseInternship) {
		this.courseInternship = courseInternship;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDay() {
		return this.endDay;
	}

	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}

	public String getNameInternship() {
		return this.nameInternship;
	}

	public void setNameInternship(String nameInternship) {
		this.nameInternship = nameInternship;
	}

	public Date getStartDay() {
		return this.startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<InternshipsStudent> getInternshipsStudents() {
		return this.internshipsStudents;
	}

	public void setInternshipsStudents(List<InternshipsStudent> internshipsStudents) {
		this.internshipsStudents = internshipsStudents;
	}

	public InternshipsStudent addInternshipsStudent(InternshipsStudent internshipsStudent) {
		getInternshipsStudents().add(internshipsStudent);
		internshipsStudent.setInternship(this);

		return internshipsStudent;
	}

	public InternshipsStudent removeInternshipsStudent(InternshipsStudent internshipsStudent) {
		getInternshipsStudents().remove(internshipsStudent);
		internshipsStudent.setInternship(null);

		return internshipsStudent;
	}

	public List<Report> getReports() {
		return this.reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setInternship(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setInternship(null);

		return report;
	}

}