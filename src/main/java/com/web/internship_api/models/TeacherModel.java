package com.web.internship_api.models;

import java.util.Date;

public class TeacherModel {
	private int id;
	
	private String fullName;
	
	private String email;
	
	private Date dob;
	
	private String phone;
	
	private String address;
	
	private String sex;
	
	private String specialize;
	
	private String level;

	private int salary;
	
	private String avatar;

	public TeacherModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherModel(int id, String fullName, String email, Date dob, String phone,
			String address, String sex, String specialize, String level, int salary, String avatar) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.sex = sex;
		this.specialize = specialize;
		this.level = level;
		this.salary = salary;
		this.avatar = avatar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSpecialize() {
		return specialize;
	}

	public void setSpecialize(String specialize) {
		this.specialize = specialize;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
