package com.web.internship_api.models;

import java.util.Date;

public class EvaluateModel {
	private int id;
	private int internshipsStudentId;
	private int score;
	private Date evaluateDay;
	private String evaluateContent;
	public EvaluateModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EvaluateModel(int id, int internshipsStudentId, int score, Date evaluateDay, String evaluateContent) {
		super();
		this.id = id;
		this.internshipsStudentId = internshipsStudentId;
		this.score = score;
		this.evaluateDay = evaluateDay;
		this.evaluateContent = evaluateContent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInternshipsStudentId() {
		return internshipsStudentId;
	}
	public void setInternshipsStudentId(int internshipsStudentId) {
		this.internshipsStudentId = internshipsStudentId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getEvaluateDay() {
		return evaluateDay;
	}
	public void setEvaluateDay(Date evaluateDay) {
		this.evaluateDay = evaluateDay;
	}
	public String getEvaluateContent() {
		return evaluateContent;
	}
	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}
	
}
