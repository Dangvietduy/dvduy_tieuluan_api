package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	@Lob
	private String avatar;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_day")
	private Date birthDay;

	private byte deleted;

	private String department;

	private String email;

	private String name;

	private String phone;

	private String sex;

	@Column(name="year_study")
	private String yearStudy;

	//bi-directional many-to-one association to InternshipsStudent
	@OneToMany(mappedBy="student")
	private List<InternshipsStudent> internshipsStudents;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="student")
	private List<Report> reports;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name="class_id")
	private Class clazz;

	public Student() {
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

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getYearStudy() {
		return this.yearStudy;
	}

	public void setYearStudy(String yearStudy) {
		this.yearStudy = yearStudy;
	}

	public List<InternshipsStudent> getInternshipsStudents() {
		return this.internshipsStudents;
	}

	public void setInternshipsStudents(List<InternshipsStudent> internshipsStudents) {
		this.internshipsStudents = internshipsStudents;
	}

	public InternshipsStudent addInternshipsStudent(InternshipsStudent internshipsStudent) {
		getInternshipsStudents().add(internshipsStudent);
		internshipsStudent.setStudent(this);

		return internshipsStudent;
	}

	public InternshipsStudent removeInternshipsStudent(InternshipsStudent internshipsStudent) {
		getInternshipsStudents().remove(internshipsStudent);
		internshipsStudent.setStudent(null);

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
		report.setStudent(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setStudent(null);

		return report;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Class getClazz() {
		return this.clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

}