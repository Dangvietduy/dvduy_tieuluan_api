package com.web.internship_api.models;

import java.util.List;

public class ResponseStatistical {
	private ResponseStudent student;
	private TeacherModel teacher;
	private ResponseInternship internship;
	private List<ResponseAttendance> attendance;
	private int score;
	private ResponseReport fileReport;
	public ResponseStatistical() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseStudent getStudent() {
		return student;
	}
	public void setStudent(ResponseStudent student) {
		this.student = student;
	}
	public TeacherModel getTeacher() {
		return teacher;
	}
	public void setTeacher(TeacherModel teacher) {
		this.teacher = teacher;
	}
	public ResponseInternship getInternship() {
		return internship;
	}
	public void setInternship(ResponseInternship internship) {
		this.internship = internship;
	}
	public List<ResponseAttendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(List<ResponseAttendance> attendance) {
		this.attendance = attendance;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ResponseReport getFileReport() {
		return fileReport;
	}
	public void setFileReport(ResponseReport fileReport) {
		this.fileReport = fileReport;
	}
	
}
