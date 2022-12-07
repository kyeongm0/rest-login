package com.lcomputerstudy.example.domain;

public class Answer {
	private int aId;
	private int sId;
	private int qId;
	private String uId;
	private String aWriter;
	private String aDateTime;
	private String value;
	private int count;
	
	
	public String getaDateTime() {
		return aDateTime;
	}
	public void setaDateTime(String aDateTime) {
		this.aDateTime = aDateTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public int getaId() {
		return aId;
	}
	public void setaId(int aId) {
		this.aId = aId;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getaWriter() {
		return aWriter;
	}
	public void setaWriter(String aWriter) {
		this.aWriter = aWriter;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
