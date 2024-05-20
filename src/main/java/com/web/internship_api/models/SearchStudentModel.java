package com.web.internship_api.models;

public class SearchStudentModel {
	private String name;
	private String className;
	private String year_study;
	public SearchStudentModel() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getYear_study() {
		return year_study;
	}
	public void setYear_study(String year_study) {
		this.year_study = year_study;
	}
	
}
