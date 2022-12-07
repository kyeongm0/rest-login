package com.lcomputerstudy.example.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardJoinRequest {
	private int bId;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String uId;
	private String bDateTime;
	private int bGroup;
	private int bOrder;
	private int bDepth;
	private List upList;
	
	
	public List getUpList() {
		return upList;
	}
	public void setUpList(List upList) {
		this.upList = upList;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbOrder() {
		return bOrder;
	}
	public void setbOrder(int bOrder) {
		this.bOrder = bOrder;
	}
	public int getbDepth() {
		return bDepth;
	}
	public void setbDepth(int bDepth) {
		this.bDepth = bDepth;
	}
	public int getbId() {
		return bId;
	}
	public void setbSearchId(int bId) {
		this.bId = bId;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public String getbDateTime() {
		return bDateTime;
	}
	public void setbDateTime(String bDateTime) {
		this.bDateTime = bDateTime;
	}
	@Override
	public String toString() {
		return "BoardJoinRequest [bId=" + bId + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bWriter=" + bWriter
				+ ", bDateTime=" + bDateTime + "]";
	}
	
	
	
}
