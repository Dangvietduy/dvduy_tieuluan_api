package com.web.internship_api.models;

public class InternshipStudentModel {
	private int internshipId;
	private int studentId;
	public InternshipStudentModel() {

	}
	public InternshipStudentModel(int internshipId, int studentId) {
		super();
		this.internshipId = internshipId;
		this.studentId = studentId;
	}
	public int getInternshipId() {
		return internshipId;
	}
	public void setInternshipId(int internshipId) {
		this.internshipId = internshipId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
