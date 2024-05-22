package com.web.internship_api.models;

import com.web.internship_api.entities.Major;
import com.web.internship_api.entities.Teacher;

public class ClassModel {
	
	private int id;

	private String code;

	private String name;
	
	private Major majorId;
	
	private Teacher teacherId;
	
	public ClassModel() {
		
	}

	public ClassModel(int id, String code, String name, Major majorId, Teacher teacherId) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.majorId = majorId;
		this.teacherId = teacherId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Major getMajorId() {
		return majorId;
	}

	public void setMajorId(Major majorId) {
		this.majorId = majorId;
	}

	public Teacher getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Teacher teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "ClassModel [id=" + id + ", code=" + code + ", name=" + name + ", majorId=" + majorId + ", teacherId="
				+ teacherId + "]";
	}
	
}
