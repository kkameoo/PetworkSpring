package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkVo;
import com.himedia.services.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// 전체 조회 (READ)
	@GetMapping("")
	public ResponseEntity<List<BoardVo>> selectAllBoardList() {
		List<BoardVo> boardList = boardService.selectAllBoard();
		return ResponseEntity.ok(boardList);
	}
	
	// 산책 게시물 전체 조회 (READ)
	@GetMapping("/walk")
	public ResponseEntity<?> selectAllBoardWalkList() {
		List<BoardWalkVo> boardWalkVos = boardService.selectAllBoardWalk();
		return ResponseEntity.ok(boardWalkVos);
	}
	// 거래 게시물 전체 조회 (READ)
	@GetMapping("/trade")
	public ResponseEntity<?> selectAllBoardTradeList() {
		List<BoardTradeVo> boardTradeVos = boardService.selectAllBoardTrade();
		return ResponseEntity.ok(boardTradeVos);
	}
	// 고용 게시물 전체 조회 (READ)
	@GetMapping("/hire")
	public ResponseEntity<?> selectAllBoardHireList() {
		List<BoardHireVo> boardHireVos = boardService.selectAllBoardHire();
		return ResponseEntity.ok(boardHireVos);
	}
	// 산책 게시물 전체 조회 (READ)
	@GetMapping("/walk/{id}")
	public ResponseEntity<?> selectBoardWalk(@PathVariable Integer id) {
		BoardWalkVo boardWalkVo = boardService.selectBoardWalk(id);
		return ResponseEntity.ok(boardWalkVo);
	}
	
	// 특정 테이블 조회 (READ)
	@GetMapping("/{id}")
	public ResponseEntity<?> selectAllBoardList(@PathVariable Integer id) {
		BoardVo board = boardService.selectOneBoard(id);
		if (board != null) {
			return ResponseEntity.ok(board);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}	
	
	// 산책 테이블을 입력 (INSERT)
	@PostMapping("/walk")
	public ResponseEntity<?> insertBoardWalk(@RequestBody BoardWalkVo boardWalkVo) {
		
		int result = boardService.insertBoardWalk(boardWalkVo);
		if (result == 1) {
			return ResponseEntity.ok(boardWalkVo);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}
	
	// 거래 테이블을 입력 (INSERT)
	@PostMapping("/trade")
	public ResponseEntity<?> insertBoardTrade(@RequestBody BoardTradeVo boaedTradeVo) {
			
		int result = boardService.insertBoardTrade(boaedTradeVo);
		if (result == 1) {
			return ResponseEntity.ok(boaedTradeVo);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}
	
	// 고용 테이블을 입력 (INSERT)
	@PostMapping("/hire")
	public ResponseEntity<?> insertBoardHire(@RequestBody BoardHireVo boardHireVo) {
				
		int result = boardService.insertBoardHire(boardHireVo);
		if (result == 1) {
			return ResponseEntity.ok(boardHireVo);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}
	
	// 특정 테이블을 수정 (UPDATE)
		@PutMapping("/{id}")
		public ResponseEntity<?> updateBoard(@RequestBody BoardVo board, @PathVariable Integer id) {
			board.setBoardId(id);
			int result = boardService.updateBoard(board);
			if (result == 1) {
				return ResponseEntity.ok(board);
			}
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		
		// 특정 테이블을 삭제 (DELETE)
		@DeleteMapping("/{id}")
		public ResponseEntity<?> deleteBoard(@PathVariable Integer id) {

			int result = boardService.deleteBoard(id);
			if (result == 1) {
				return ResponseEntity.ok("정상적으로 삭제되었습니다.");
			}
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		
		//Click 카운트를 1 증가
		@PutMapping("/count/{id}")
		public ResponseEntity<?> increaseCount(@PathVariable Integer id) {

			int result = boardService.increaseCount(id);
			if (result == 1) {
				return ResponseEntity.ok("정상적으로 카운트수 증가.");
			}
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		
		
}
