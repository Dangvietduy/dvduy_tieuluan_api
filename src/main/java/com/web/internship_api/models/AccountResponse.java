package com.web.internship_api.models;

public class AccountResponse {
	private int id;
	private String username;
	private String token;
	private String role;
	public AccountResponse() {};
	public AccountResponse(int id, String username, String token, String role) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
