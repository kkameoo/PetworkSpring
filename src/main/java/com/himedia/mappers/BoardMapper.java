package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardVo;

@Mapper
public interface BoardMapper {
	// 모든 게시물들을 가져오는 sql
	List<BoardVo> selectAllBoard();
	// 특정 게시물을 가져오는 sql
	BoardVo selectBoard(Integer id);
	// 특정 게시물을 입력하는 sql
	int insertBoard(BoardVo board);
	// 특정 게시물을 수정하는 sql
	int updateBoard(BoardVo board);
	// 특정 게시물을 삭제하는 sql
	int deleteBoard(Integer id);
	// 특정 게시물의 클릭 count를 증가하는 sql
	int increaseClickCount(Integer id); 
	// 특정 게시물의 신고 count 증가
//	<update id="increaseReportCount" parameterType="int">
	int increaseReportCount(Integer id);
	
}
