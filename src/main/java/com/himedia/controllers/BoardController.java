package com.himedia.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.himedia.repository.vo.BoardCommentVo;
import com.himedia.repository.vo.BoardHireRequestVo;
import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardTradeRequestVo;
import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardVo;
import com.himedia.repository.vo.BoardWalkRequestVo;
import com.himedia.repository.vo.BoardWalkVo;
import com.himedia.services.BoardCommentService;
import com.himedia.services.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardCommentService boardCommentService;

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
	// 특정 산책 게시물 조회 (READ)
	@GetMapping("/walk/{id}")
	public ResponseEntity<?> selectBoardWalk(@PathVariable Integer id) {
		BoardWalkVo boardWalkVo = boardService.selectBoardWalk(id);
		return ResponseEntity.ok(boardWalkVo);
	}
	// 특정 나눔 게시물 조회 (READ)
	@GetMapping("/trade/{id}")
	public ResponseEntity<?> selectBoardTrade(@PathVariable Integer id) {
		BoardTradeVo boardTradeVo = boardService.selectBoardTrade(id);
		return ResponseEntity.ok(boardTradeVo);
	}
	// 특정 고용 게시물 조회 (READ)
	@GetMapping("/hire/{id}")
	public ResponseEntity<?> selectBoardHire(@PathVariable Integer id) {
		BoardHireVo boardHireVo = boardService.selectBoardHire(id);
		return ResponseEntity.ok(boardHireVo);
	}
	
	// 산책 게시물 인기순
	@GetMapping("/walk/popular")
	public List<BoardWalkVo> getPopularBoardWalk() {
	    return boardService.getPopularBoardWalk();
	}
	
	// 고용 게시물 인기순
	@GetMapping("/hire/popular")
	public List<BoardHireVo> getPopularBoardHire() {
	    return boardService.getPopularBoardHire();
	}
	
	// 거래 게시물 인기순
	@GetMapping("/trade/popular")
	public List<BoardTradeVo> getPopularBoardTrade() {
		return boardService.getPopularBoardTrade();
	}


	// userid로 테이블 조회
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<BoardVo>> getBoardsByUserId(@PathVariable int userId) {
	    return ResponseEntity.ok(boardService.selectBoardByUserId(userId));
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
	public ResponseEntity<?> insertAllBoardWalk(@RequestParam("file") MultipartFile file,
			@RequestParam("requestJson") String requestJson) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BoardWalkRequestVo boardWalkRequestVo = objectMapper.readValue(requestJson, BoardWalkRequestVo.class);
		int result = boardService.insertBoardWalk(file, boardWalkRequestVo);
		if (result == 1) {
			return ResponseEntity.ok(boardWalkRequestVo);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}
	
	// 거래 테이블을 입력 (INSERT)
	@PostMapping("/trade")
	public ResponseEntity<?> insertBoardTrade(@RequestParam("file") MultipartFile file,
			@RequestParam("requestJson") String requestJson) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BoardTradeRequestVo boardTradeRequestVo = objectMapper.readValue(requestJson, BoardTradeRequestVo.class);
		int result = boardService.insertBoardTrade(file, boardTradeRequestVo);
		if (result == 1) {
			return ResponseEntity.ok(boardTradeRequestVo);
		}
		return ResponseEntity.badRequest().body("에러가 발생했습니다.");
	}
	
	// 고용 테이블을 입력 (INSERT)
	@PostMapping("/hire")
	public ResponseEntity<?> insertBoardHire(@RequestParam("file") MultipartFile file,
			@RequestParam("requestJson") String requestJson) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		BoardHireRequestVo boardHireRequestVo = objectMapper.readValue(requestJson, BoardHireRequestVo.class);
		int result = boardService.insertBoardHire(file, boardHireRequestVo);
		if (result == 1) {
			return ResponseEntity.ok(boardHireRequestVo);
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
		
	// 산책 테이블을 수정 (UPDATE)
	@PutMapping("/walk/{id}")
	public ResponseEntity<?> updateBoardWalk(@RequestBody BoardWalkVo boardWalkVo, @PathVariable Integer id) {
		boardWalkVo.setBoardId(id);
		int result = boardService.updateBoardWalk(boardWalkVo);
		if (result == 0) {
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		return ResponseEntity.ok(boardWalkVo);
	}
	
	// 산책 테이블을 수정 (UPDATE)
	@PutMapping("/trade/{id}")
	public ResponseEntity<?> updateBoardTrade(@RequestBody BoardTradeVo boardTradeVo, @PathVariable Integer id) {
		boardTradeVo.setBoardId(id);
		int result = boardService.updateBoardTrade(boardTradeVo);
		if (result == 0) {
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		return ResponseEntity.ok(boardTradeVo);
	}
	
	// 고용 테이블을 수정 (UPDATE)
	@PutMapping("/hire/{id}")
	public ResponseEntity<?> updateBoardHire(@RequestBody BoardHireVo boardHireVo, @PathVariable Integer id) {
		boardHireVo.setBoardId(id);
		int result = boardService.updateBoardHire(boardHireVo);
		if (result == 0) {
			return ResponseEntity.badRequest().body("에러가 발생했습니다.");
		}
		return ResponseEntity.ok(boardHireVo);
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
	
	// 댓글 등록
	@PostMapping("/{boardId}/comment")
	public ResponseEntity<String> insertBoardComment(@PathVariable int boardId, @RequestBody BoardCommentVo comment){
		comment.setBoardId(boardId);
		boardCommentService.insertBoardComment(comment);
		return ResponseEntity.ok("댓글이 등록되었습니다.");
	}
	
	// 댓글 전체 조회
	@GetMapping("/{boardId}/comment")
	public ResponseEntity<List<BoardCommentVo>> getBoardComments(@PathVariable int boardId){
		List<BoardCommentVo> comments = boardCommentService.selectBoardCommentsByBoardId(boardId);
		return ResponseEntity.ok(comments);
	}
	
	@PostMapping("/petstagram")
	public ResponseEntity<?> insertPetstagramPost(@RequestParam("file") MultipartFile file,
	                                              @RequestParam("requestJson") String requestJson) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    BoardVo boardVo = objectMapper.readValue(requestJson, BoardVo.class);

	    // 예외처리: boardType이 반드시 4여야 함
	    if (boardVo.getBoardType() != 4) {
	        return ResponseEntity.badRequest().body("boardType은 4 (펫스타그램) 이어야 합니다.");
	    }

	    int result = boardService.insertOneBoard(file, boardVo);
	    if (result == 1) {
	        return ResponseEntity.ok(boardVo);
	    }
	    return ResponseEntity.badRequest().body("게시글 등록 중 오류가 발생했습니다.");
	}

	
}
