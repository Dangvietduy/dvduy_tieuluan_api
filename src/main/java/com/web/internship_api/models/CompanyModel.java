package com.web.internship_api.models;

import java.util.Objects;

public class CompanyModel {

	private int id;

	private String address;

	private String email;

	private String industry;

	private String name;

	private String phone;

	public CompanyModel() {
		
	}
	
	public CompanyModel(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CompanyModel(int id, String address, String email, String industry, String name, String phone) {
		super();
		this.id = id;
		this.address = address;
		this.email = email;
		this.industry = industry;
		this.name = name;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, industry, name, phone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyModel other = (CompanyModel) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(industry, other.industry) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}

	@Override
	public String toString() {
		return "CompanyModel [id=" + id + ", address=" + address + ", email=" + email + ", industry=" + industry
				+ ", name=" + name + ", phone=" + phone + "]";
	}
	
	
}
