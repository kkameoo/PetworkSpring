package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.BoardVo;

@Service
public interface BoardService {
	// 모든 게시물들을 받아오는 기능
	List<BoardVo> selectAllBoard();
	// 특정 게시물을 받아오는 기능
	BoardVo selectOneBoard(Integer id);
	// 특정 게시물을 입력하는 기능
	int insertBoard(BoardVo board);
}
