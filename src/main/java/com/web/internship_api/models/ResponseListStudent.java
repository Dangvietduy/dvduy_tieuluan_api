package com.web.internship_api.models;

public class ResponseListStudent {
	private ResponseStudent students;
	private ResponseInternship internship;
	public ResponseListStudent() {
		
	}

	public ResponseListStudent(ResponseStudent students, ResponseInternship internship) {
		super();
		this.students = students;
		this.internship = internship;
	}

	public ResponseStudent getStudents() {
		return students;
	}
	public void setStudents(ResponseStudent students) {
		this.students = students;
	}

	public ResponseInternship getInternship() {
		return internship;
	}

	public void setInternship(ResponseInternship internship) {
		this.internship = internship;
	}

	
}
