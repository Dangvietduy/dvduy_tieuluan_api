package com.web.internship_api.models;

import java.util.Date;

public class ResponseReport {
	private int id;
	private Date reportDay;
	private String name;
    private String url;
    private byte[] file;
	public ResponseReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResponseReport(int id, Date reportDay, String name, String url, byte[] file) {
		super();
		this.id = id;
		this.reportDay = reportDay;
		this.name = name;
		this.url = url;
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getReportDay() {
		return reportDay;
	}
	public void setReportDay(Date reportDay) {
		this.reportDay = reportDay;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
    
}
