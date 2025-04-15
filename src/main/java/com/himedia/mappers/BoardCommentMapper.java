package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardCommentVo;

@Mapper
public interface BoardCommentMapper {

	// 댓글 등록
	void insertBoardComment(BoardCommentVo comment);

	// 특정 게시글의 댓글 전체 조회
	List<BoardCommentVo> selectBoardCommentsByBoardId(int boardId);

}
