package com.lcomputerstudy.example.service;

import java.util.List;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.Search;
import com.lcomputerstudy.example.domain.Upload;


public interface BoardService {
	public List<Board> selectBoardList(Board board);
	
	public Board selectBoardDetail(int bId);
	
	public void selectBoardEdit(Board board);
	
	public void deleteBoard(int bId);
	
	public List<Upload> uploadRead(int bId);
	
	public void uploadDelete(int bId);
	
	public void createBoard(Board board);
	
	public void uploadInsert(Upload upload);
	
	public List<Upload> uploadList(Upload upload);
	
	public void uploadUpdate(Upload upload);
	
	public void createReply(Board board);
	
	public int boardCount();
	
	public int boardSearchCount(Search search);
	
	public List<Board> boardSearchList(Board board);
	
}
