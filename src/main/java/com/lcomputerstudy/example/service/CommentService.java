package com.lcomputerstudy.example.service;

import java.util.List;

import com.lcomputerstudy.example.domain.Comment;

public interface CommentService {
	
	public List<Comment> selectCommentList(Comment comment);
	
	public int commentCount(Comment comment);
	
	public void createComment(Comment comment);
	
	public void replyComment(Comment comment);
	
	public void selectCommentEdit(Comment comment);
	
	public void selectCommentDelete(int cId);
}
