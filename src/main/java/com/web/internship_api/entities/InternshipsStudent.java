package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the internships_students database table.
 * 
 */
@Entity
@Table(name="internships_students")
@NamedQuery(name="InternshipsStudent.findAll", query="SELECT i FROM InternshipsStudent i")
public class InternshipsStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to AttendanceCheck
	@OneToMany(mappedBy="internshipsStudent")
	@JsonIgnore
	private List<AttendanceCheck> attendanceChecks;

	//bi-directional many-to-one association to Evaluate
	@OneToMany(mappedBy="internshipsStudent")
	@JsonIgnore
	private List<Evaluate> evaluates;

	//bi-directional many-to-one association to Internship
	@ManyToOne
	private Internship internship;

	//bi-directional many-to-one association to Student
	@ManyToOne
	private Student student;

	public InternshipsStudent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AttendanceCheck> getAttendanceChecks() {
		return this.attendanceChecks;
	}

	public void setAttendanceChecks(List<AttendanceCheck> attendanceChecks) {
		this.attendanceChecks = attendanceChecks;
	}

	public AttendanceCheck addAttendanceCheck(AttendanceCheck attendanceCheck) {
		getAttendanceChecks().add(attendanceCheck);
		attendanceCheck.setInternshipsStudent(this);

		return attendanceCheck;
	}

	public AttendanceCheck removeAttendanceCheck(AttendanceCheck attendanceCheck) {
		getAttendanceChecks().remove(attendanceCheck);
		attendanceCheck.setInternshipsStudent(null);

		return attendanceCheck;
	}

	public List<Evaluate> getEvaluates() {
		return this.evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public Evaluate addEvaluate(Evaluate evaluate) {
		getEvaluates().add(evaluate);
		evaluate.setInternshipsStudent(this);

		return evaluate;
	}

	public Evaluate removeEvaluate(Evaluate evaluate) {
		getEvaluates().remove(evaluate);
		evaluate.setInternshipsStudent(null);

		return evaluate;
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