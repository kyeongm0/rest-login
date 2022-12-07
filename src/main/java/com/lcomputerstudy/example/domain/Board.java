package com.lcomputerstudy.example.domain;

import java.util.List;

public class Board {
	private int bId;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String uId;
	private String bDateTime;
	private int bGroup;
	private int bOrder;
	private int bDepth;
	private int bOrigin;
	private Pagination pagination;
	private Search search;
	private List<Upload> uploadList;
	
	
	public List<Upload> getUploadList() {
		return uploadList;
	}
	public void setUploadList(List<Upload> uploadList) {
		this.uploadList = uploadList;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public int getbOrigin() {
		return bOrigin;
	}
	public void setbOrigin(int bOrigin) {
		this.bOrigin = bOrigin;
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
	public void setbId(int bId) {
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
		return "Board [bId=" + bId + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bWriter=" + bWriter
				+ ", bDateTime=" + bDateTime + "]";
	}
	
}
