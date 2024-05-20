package com.web.internship_api.models;

import java.util.Date;

public class InternShipModel {
	private int id;
	private String nameInternShip;
	private Date startDay;
	private Date endDay;
	private int courseInternShip;
	private String description;
	private String address;
	private int teacher_id;
	public InternShipModel() {
		
	}
	public InternShipModel(int id, String nameInternShip, Date startDay, Date endDay, int courseInternShip,
			String description, String address, int teacher_id) {
		super();
		this.id = id;
		this.nameInternShip = nameInternShip;
		this.startDay = startDay;
		this.endDay = endDay;
		this.courseInternShip = courseInternShip;
		this.description = description;
		this.address = address;
		this.teacher_id = teacher_id;
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
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	
}
