package com.web.internship_api.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the attendance_check database table.
 * 
 */
@Entity
@Table(name="attendance_check")
@NamedQuery(name="AttendanceCheck.findAll", query="SELECT a FROM AttendanceCheck a")
public class AttendanceCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="attendance_day_check")
	private Date attendanceDayCheck;

	private byte deleted;

	private byte status;

	//bi-directional many-to-one association to InternshipsStudent
	@ManyToOne
	@JoinColumn(name="internships_students_id")
	private InternshipsStudent internshipsStudent;

	public AttendanceCheck() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAttendanceDayCheck() {
		return this.attendanceDayCheck;
	}

	public void setAttendanceDayCheck(Date attendanceDayCheck) {
		this.attendanceDayCheck = attendanceDayCheck;
	}

	public byte getDeleted() {
		return this.deleted;
	}

	public void setDeleted(byte deleted) {
		this.deleted = deleted;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public InternshipsStudent getInternshipsStudent() {
		return this.internshipsStudent;
	}

	public void setInternshipsStudent(InternshipsStudent internshipsStudent) {
		this.internshipsStudent = internshipsStudent;
	}

}