package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the evaluate database table.
 * 
 */
@Entity
@NamedQuery(name="Evaluate.findAll", query="SELECT e FROM Evaluate e")
public class Evaluate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private byte deleted;

	@Column(name="evaluate_content")
	private String evaluateContent;

	@Temporal(TemporalType.DATE)
	@Column(name="evaluate_day")
	private Date evaluateDay;

	private int score;

	//bi-directional many-to-one association to InternshipsStudent
	@ManyToOne
	@JoinColumn(name="internships_students_id")
	private InternshipsStudent internshipsStudent;

	public Evaluate() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public String getEvaluateContent() {
		return this.evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public Date getEvaluateDay() {
		return this.evaluateDay;
	}

	public void setEvaluateDay(Date evaluateDay) {
		this.evaluateDay = evaluateDay;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public InternshipsStudent getInternshipsStudent() {
		return this.internshipsStudent;
	}

	public void setInternshipsStudent(InternshipsStudent internshipsStudent) {
		this.internshipsStudent = internshipsStudent;
	}

}