package com.lcomputerstudy.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lcomputerstudy.example.domain.Board;
import com.lcomputerstudy.example.domain.Pagination;
import com.lcomputerstudy.example.domain.Search;
import com.lcomputerstudy.example.domain.Upload;

@Mapper
public interface BoardMapper {
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
	
	public void groupUpdate(Board board);
	
	public void hitUpdate(int bId);
	
	public void createReply(Board board);
	
	public void orderUpdate(Board board);
	
	public void depthUpdate(Board board);
	
	public int boardCount();
	
	public int boardSearchCount(Search search);
	
	public List<Board> boardSearchList(Board board);
}
