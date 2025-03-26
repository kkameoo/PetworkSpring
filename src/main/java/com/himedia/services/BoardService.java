package com.himedia.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.BoardHireRequestVo;
import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardTradeRequestVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkRequestVo;
import com.himedia.repository.vo.BoardWalkVo;

@Service
public interface BoardService {
	// 모든 게시물들을 받아오는 기능
	List<BoardVo> selectAllBoard();
	// 특정 게시물을 받아오는 기능
	BoardVo selectOneBoard(Integer id);
	// 산책 게시물을 입력하는 기능
	int insertBoardWalk(MultipartFile file, BoardWalkRequestVo boardWalkRequestVo) throws IOException;
	// 거래 게시물을 입력하는 기능
	int insertBoardTrade(MultipartFile file, BoardTradeRequestVo boardTradeRequestVo) throws IOException;
	// 고용 게시물을 입력하는 기능
	int insertBoardHire(MultipartFile file, BoardHireRequestVo boardHireRequestVo) throws IOException;
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
	// 산책 게시물을 수정하는 기능
	int updateBoardWalk(BoardWalkVo boardWalkVo);
	// 거래 게시물을 수정하는 기능
	int updateBoardTrade(BoardTradeVo boardTradeVo);
	// 고용 게시물을 수정하는 기능
	int updateBoardHire(BoardHireVo boardHireVo);
	// 펫스타그램 게시글
	int insertOneBoard(MultipartFile file, BoardVo boardVo) throws IOException;

	
	// 산책 게시물 + 이미지를 입력하는 기능
	
}
