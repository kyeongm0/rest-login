package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Comment;
import com.lcomputerstudy.example.mapper.CommentMapper;

@Service("CommentServiceImple")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper commentMapper;
	
	@Override
	public List<Comment> selectCommentList(Comment comment) {
		return commentMapper.selectCommentList(comment);
	}
	
	@Override
	public int commentCount(Comment comment) {
		return commentMapper.commentCount(comment);
	}
	
	@Override
	public void createComment(Comment comment) {
		commentMapper.createComment(comment);
		commentMapper.groupUpdate(comment);
	}
	
	@Override
	public void replyComment(Comment comment) {
		commentMapper.createCommentReply(comment);
		commentMapper.orderUpdate(comment);
		commentMapper.depthUpdate(comment);
	}
	
	@Override
	public void selectCommentEdit(Comment comment) {
		commentMapper.selectCommentEdit(comment);
	}
	
	@Override
	public void selectCommentDelete(int cId) {
		commentMapper.selectCommentDelete(cId);
	}
	
	
}
