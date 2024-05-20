package com.web.internship_api.models;

public class SearchInternship {
	private String nameInternship;
	private int courseInternship;
	private String nameTeacher;
	public SearchInternship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNameInternship() {
		return nameInternship;
	}
	public void setNameInternship(String nameInternship) {
		this.nameInternship = nameInternship;
	}
	public int getCourseInternship() {
		return courseInternship;
	}
	public void setCourseInternship(int courseInternship) {
		this.courseInternship = courseInternship;
	}
	public String getNameTeacher() {
		return nameTeacher;
	}
	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}
	
}
