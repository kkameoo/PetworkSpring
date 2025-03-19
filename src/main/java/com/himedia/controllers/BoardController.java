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

import com.himedia.repository.vo.BoardVo;
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
	
	// 특정 테이블 조회 (READ)
	@GetMapping("/{id}")
	public ResponseEntity<?> selectAllBoardList(@PathVariable Integer id) {
		BoardVo board = boardService.selectOneBoard(id);
		if (board != null) {
			return ResponseEntity.ok(board);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}	
	
	// 특정 테이블을 입력 (INSERT)
	@PostMapping("")
	public ResponseEntity<?> insertBoard(@RequestBody BoardVo board) {
		int result = boardService.insertBoard(board);
		if (result == 1) {
			return ResponseEntity.ok(board);
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
