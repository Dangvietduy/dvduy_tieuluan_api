package com.web.internship_api.models;

import java.util.Date;

public class StudentModel {

	private String email;

	private String fullname;

	private String  sex;

	private Date dob;
	
	private String phone;

	private String avatar;
	
	private String address;
	
	private String department;
	
	private String year_study;
	
	private int class_id;

	public StudentModel() {
	
	}

	public StudentModel(String email, String fullname, String sex, Date dob, String phone, String avatar,
			String address, String department, String year_study, int class_id) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.sex = sex;
		this.dob = dob;
		this.phone = phone;
		this.avatar = avatar;
		this.address = address;
		this.department = department;
		this.year_study = year_study;
		this.class_id = class_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getYear_study() {
		return year_study;
	}

	public void setYear_study(String year_study) {
		this.year_study = year_study;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
}
