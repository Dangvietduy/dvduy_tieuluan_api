package com.web.internship_api.models;

import java.util.Date;
import java.util.List;

public class ResponseInternship {
	private int id;
	private String nameInternShip;
	private Date startDay;
	private Date endDay;
	private int courseInternShip;
	private String description;
	private String address;
	private int teacherId;
	private String teacherName;
	private List<ResponseStudent> students;
	
	public ResponseInternship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameInternShip() {
		return nameInternShip;
	}
	public void setNameInternShip(String nameInternShip) {
		this.nameInternShip = nameInternShip;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public int getCourseInternShip() {
		return courseInternShip;
	}
	public void setCourseInternShip(int courseInternShip) {
		this.courseInternShip = courseInternShip;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public List<ResponseStudent> getStudents() {
		return students;
	}
	public void setStudents(List<ResponseStudent> students) {
		this.students = students;
	}
}
