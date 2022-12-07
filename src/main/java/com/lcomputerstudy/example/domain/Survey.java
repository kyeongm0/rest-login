package com.lcomputerstudy.example.domain;

import java.util.List;

public class Survey {
	private int sId;
	private String sTitle;
	private String sSubTitle;
	private String sWriter;
	private String sDateTime;
	private List<Question> questionList;
	private String uId;
	private Pagination pagination;
	
	
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public List<Question> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsSubTitle() {
		return sSubTitle;
	}
	public void setsSubTitle(String sSubTitle) {
		this.sSubTitle = sSubTitle;
	}
	public String getsWriter() {
		return sWriter;
	}
	public void setsWriter(String sWriter) {
		this.sWriter = sWriter;
	}
	public String getsDateTime() {
		return sDateTime;
	}
	public void setsDateTime(String sDateTime) {
		this.sDateTime = sDateTime;
	}
	
}
