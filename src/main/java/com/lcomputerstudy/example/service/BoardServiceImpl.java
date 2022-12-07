package com.lcomputerstudy.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.Search;
import com.lcomputerstudy.example.domain.Upload;
import com.lcomputerstudy.example.mapper.BoardMapper;



@Service("BoardServiceImple")
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardMapper;
	
	
	@Override
	public List<Board> selectBoardList(Board board) {
		return boardMapper.selectBoardList(board);
	}


	@Override
	public Board selectBoardDetail(int bId) {
		boardMapper.hitUpdate(bId);
		return boardMapper.selectBoardDetail(bId);
	}
	
	@Override
	public void selectBoardEdit(Board board) {
		boardMapper.selectBoardEdit(board);
	}
	
	@Override
	public void deleteBoard(int bId) {
		boardMapper.deleteBoard(bId);
	}
	
	@Override
	public List<Upload> uploadRead(int bId) {
		return boardMapper.uploadRead(bId);
	}

	@Override
	public void uploadDelete(int bId) {
		boardMapper.uploadDelete(bId);
	}
	
	@Override
	public void createBoard(Board board) {
		boardMapper.createBoard(board);
		boardMapper.groupUpdate(board);
	}
	
	@Override
	public void uploadInsert(Upload upload) {
		boardMapper.uploadInsert(upload);
	}
	
	@Override
	public List<Upload> uploadList(Upload upload) {
		return boardMapper.uploadList(upload);
	}
	
	@Override
	public void uploadUpdate(Upload upload) {
		boardMapper.uploadUpdate(upload);
	}
	
	@Override
	public void createReply(Board board) {
		boardMapper.createReply(board);
		boardMapper.orderUpdate(board);
		boardMapper.depthUpdate(board);
	}
	
	@Override
	public int boardCount() {
		return boardMapper.boardCount();
	}
	
	@Override
	public int boardSearchCount(Search search) {
		return boardMapper.boardSearchCount(search);
	}
	
	@Override
	public List<Board> boardSearchList(Board board) {
		return boardMapper.boardSearchList(board);
	}
}
