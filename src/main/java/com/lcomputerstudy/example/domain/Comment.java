package com.lcomputerstudy.example.domain;

public class Comment {
	private int cId;
	private String cContent;
	private String cWriter;
	private String cDateTime;
	private int bId;
	private int cGroup;
	private int cOrder;
	private int cDepth;
	private Pagination pagination;
	private int cShowReply;
	private int cShowEdit;
	private int cOrigin;
	private String uId;
	
	
	
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public int getcOrigin() {
		return cOrigin;
	}
	public void setcOrigin(int cOrigin) {
		this.cOrigin = cOrigin;
	}
	public int getcShowReply() {
		return cShowReply;
	}
	public void setcShowReply(int cShowReply) {
		this.cShowReply = cShowReply;
	}
	public int getcShowEdit() {
		return cShowEdit;
	}
	public void setcShowEdit(int cShowEdit) {
		this.cShowEdit = cShowEdit;
	}
	public String getcDateTime() {
		return cDateTime;
	}
	public void setcDateTime(String cDateTime) {
		this.cDateTime = cDateTime;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public String getcWriter() {
		return cWriter;
	}
	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}
	public int getcGroup() {
		return cGroup;
	}
	public void setcGroup(int cGroup) {
		this.cGroup = cGroup;
	}
	public int getcOrder() {
		return cOrder;
	}
	public void setcOrder(int cOrder) {
		this.cOrder = cOrder;
	}
	public int getcDepth() {
		return cDepth;
	}
	public void setcDepth(int cDepth) {
		this.cDepth = cDepth;
	}
	@Override
	public String toString() {
		return "Comment [cId=" + cId + ", cContent=" + cContent + ", cWriter=" + cWriter + ", cDateTime=" + cDateTime
				+ ", bId=" + bId + ", cGroup=" + cGroup + ", cOrder=" + cOrder + ", cDepth=" + cDepth + ", pagination="
				+ pagination + ", cShowReply=" + cShowReply + ", cShowEdit=" + cShowEdit + "]";
	}
	
	
	
	
	
	
}
