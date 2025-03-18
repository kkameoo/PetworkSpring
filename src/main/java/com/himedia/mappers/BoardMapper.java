package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardVo;

@Mapper
public interface BoardMapper {
	// 모든 게시물들을 가져오는 기능
	List<BoardVo> selectAllBoard();
	// 특정 게시물을 가져오는 기능
	BoardVo selectBoard(Integer id);
	// 특정 게시물을 입력하는 기능
	int insertBoard(BoardVo board);
}
