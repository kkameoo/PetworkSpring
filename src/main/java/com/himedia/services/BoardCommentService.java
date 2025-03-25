package com.himedia.services;

import java.util.List;

import com.himedia.repository.vo.BoardCommentVo;

public interface BoardCommentService {

	// 댓글 등록
    void insertBoardComment(BoardCommentVo comment);

    // 게시글 ID로 댓글 전체 조회
    List<BoardCommentVo> selectBoardCommentsByBoardId(int boardId);
}
