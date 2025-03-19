package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.BoardMapper;
import com.himedia.repository.vo.BoardVo;
import com.himedia.services.BoardService;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardVo> selectAllBoard() {
		List<BoardVo> boardList = boardMapper.selectAllBoard();
		return boardList;
	}

	@Override
	public BoardVo selectOneBoard(Integer id) {
		BoardVo board = boardMapper.selectBoard(id);
		return board;
	}

	@Override
	public int insertBoard(BoardVo board) {
		int result = boardMapper.insertBoard(board);
		return result;
	}

	@Override
	public int updateBoard(BoardVo board) {
		int result = boardMapper.updateBoard(board);
		return result;
	}

	@Override
	public int deleteBoard(Integer id) {
		int result = boardMapper.deleteBoard(id);
		return result;
	}

	@Override
	public int increaseCount(Integer id) {
		int result = boardMapper.increaseCount(id);
		return result;
	}
	
	
	
	
}
