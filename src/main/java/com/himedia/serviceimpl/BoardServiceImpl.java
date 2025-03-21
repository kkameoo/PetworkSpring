package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.himedia.mappers.BoardHireMapper;
import com.himedia.mappers.BoardMapper;
import com.himedia.mappers.BoardTradeMapper;
import com.himedia.mappers.BoardWalkMapper;
import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkVo;
import com.himedia.services.BoardService;


@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private BoardWalkMapper boardWalkMapper;
	@Autowired
	private BoardTradeMapper boardTradeMapper;
	@Autowired
	private BoardHireMapper boardHireMapper;

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
	@Transactional
	public int insertBoardWalk(BoardWalkVo boardWalkVo) {
		BoardVo board = BoardVo.builder()
				.userId(boardWalkVo.getUserId())
				.title(boardWalkVo.getTitle())
				.content(boardWalkVo.getContent())
				.localSi(boardWalkVo.getLocalSi())
				.localGu(boardWalkVo.getLocalGu())
				.boardType(boardWalkVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		boardWalkVo.setBoardId(board.getBoardId());
		int result2 = boardWalkMapper.insertBoardWalk(boardWalkVo);
		
		return result2;
	}
	
	@Override
	@Transactional
	public int insertBoardTrade(BoardTradeVo boardTradeVo) {
		BoardVo board = BoardVo.builder()
				.userId(boardTradeVo.getUserId())
				.title(boardTradeVo.getTitle())
				.content(boardTradeVo.getContent())
				.localSi(boardTradeVo.getLocalSi())
				.localGu(boardTradeVo.getLocalGu())
				.boardType(boardTradeVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		boardTradeVo.setBoardId(board.getBoardId());
		int result2 = boardTradeMapper.insertBoardTrade(boardTradeVo);
		
		return result2;
	}
	
	@Override
	@Transactional
	public int insertBoardHire(BoardHireVo boardHireVo) {
		BoardVo board = BoardVo.builder()
				.userId(boardHireVo.getUserId())
				.title(boardHireVo.getTitle())
				.content(boardHireVo.getContent())
				.localSi(boardHireVo.getLocalSi())
				.localGu(boardHireVo.getLocalGu())
				.boardType(boardHireVo.getBoardType())
				.build();
				
		System.out.println(board);
		int result = boardMapper.insertBoard(board);
		System.out.println(board);
		boardHireVo.setBoardId(board.getBoardId());
		int result2 = boardHireMapper.insertBoardHire(boardHireVo);
		return result2;
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

	@Override
	public List<BoardWalkVo> selectAllBoardWalk() {
		List<BoardWalkVo> boardWalkVos = boardWalkMapper.selectAllBoardWalk();
		return boardWalkVos;
	}

	@Override
	public List<BoardTradeVo> selectAllBoardTrade() {
		List<BoardTradeVo> boardTradeVos = boardTradeMapper.selectAllBoardTrade();
		return boardTradeVos;
	}

	@Override
	public List<BoardHireVo> selectAllBoardHire() {
		List<BoardHireVo> boardHireVos = boardHireMapper.selectAllBoardHire();
		return boardHireVos;
	}
	
	
	
	
}
