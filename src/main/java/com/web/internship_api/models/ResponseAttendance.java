package com.web.internship_api.models;

import java.util.Date;

public class ResponseAttendance {
	private Date attendaceCheck;
	public ResponseAttendance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseAttendance(Date attendaceCheck) {
		super();
		this.attendaceCheck = attendaceCheck;
	}
	
	public Date getAttendaceCheck() {
		return attendaceCheck;
	}
	public void setAttendaceCheck(Date attendaceCheck) {
		this.attendaceCheck = attendaceCheck;
	}
	
}
