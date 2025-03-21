package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkVo;

@Service
public interface BoardService {
	// 모든 게시물들을 받아오는 기능
	List<BoardVo> selectAllBoard();
	// 특정 게시물을 받아오는 기능
	BoardVo selectOneBoard(Integer id);
	// 산책 게시물을 입력하는 기능
	int insertBoardWalk(BoardWalkVo boardWalkVo);
	// 거래 게시물을 입력하는 기능
	int insertBoardTrade(BoardTradeVo boardTradeVo);
	// 고용 게시물을 입력하는 기능
	int insertBoardHire(BoardHireVo boardHireVo);
	// 특정 게시물을 수정하는 기능
	int updateBoard(BoardVo board);
	// 특정 게시물을 삭제하는 기능
	int deleteBoard(Integer id);
	// 특정 게시물의 조회수를 증가하는 기능
	int increaseCount(Integer id);
	// 산책 게시물들을 받아오는 기능
	List<BoardWalkVo> selectAllBoardWalk();
	// 거래 게시물들을 받아오는 기능
	List<BoardTradeVo> selectAllBoardTrade();
	// 고용 게시물들을 받아오는 기능
	List<BoardHireVo> selectAllBoardHire();
	// 산책 게시물을 받아오는 기능
	BoardWalkVo selectBoardWalk(Integer id);
	// 거래 게시물을 받아오는 기능
	BoardTradeVo selectBoardTrade(Integer id);
	// 고용 게시물을 받아오는 기능
	BoardHireVo selectBoardHire(Integer id);
}
