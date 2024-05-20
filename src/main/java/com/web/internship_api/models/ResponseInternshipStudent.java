package com.web.internship_api.models;

public class ResponseInternshipStudent {
	private ResponseStudent student;
	private ResponseInternship internship;
	public ResponseInternshipStudent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseStudent getStudent() {
		return student;
	}
	public void setStudent(ResponseStudent student) {
		this.student = student;
	}
	public ResponseInternship getInternship() {
		return internship;
	}
	public void setInternship(ResponseInternship internship) {
		this.internship = internship;
	}
	
}
