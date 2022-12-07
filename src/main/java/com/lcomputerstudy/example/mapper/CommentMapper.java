package com.lcomputerstudy.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lcomputerstudy.example.domain.Comment;

@Mapper
public interface CommentMapper {
	
	public List<Comment> selectCommentList(Comment comment);
	
	public int commentCount(Comment comment);
	
	public void createComment(Comment comment);
	
	public void groupUpdate(Comment comment);
	
	public void createCommentReply(Comment comment);
	
	public void orderUpdate(Comment comment);
	
	public void depthUpdate(Comment comment);
	
	public void selectCommentEdit(Comment comment);
	
	public void selectCommentDelete(int cId);
}
