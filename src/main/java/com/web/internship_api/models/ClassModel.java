package com.web.internship_api.models;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(id, code, name, majorId, teacherId );
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassModel other = (ClassModel) obj;
		return Objects.equals(name, other.name) &&
				id == other.id &&
				Objects.equals(majorId, other.majorId) &&
				Objects.equals(teacherId, other.teacherId)
				&& Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "ClassModel [id=" + id + ", code=" + code + ", name=" + name + ", majorId=" + majorId + ", teacherId="
				+ teacherId + "]";
	}
	
}
