package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.BoardCommentMapper;
import com.himedia.repository.vo.BoardCommentVo;
import com.himedia.services.BoardCommentService;

@Service
public class BoardCommentServiceImpl implements BoardCommentService{

	 @Autowired
	 private BoardCommentMapper boardCommentMapper;
	 
	 // 댓글 등록
	 @Override
	 public void insertBoardComment(BoardCommentVo comment) {
		 boardCommentMapper.insertBoardComment(comment);
	 }
	 
	 // 댓글 조회
	 @Override
	 public List<BoardCommentVo> selectBoardCommentsByBoardId(int boardId) {
		 return boardCommentMapper.selectBoardCommentsByBoardId(boardId);
	 }
}
