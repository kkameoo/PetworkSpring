package com.himedia.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.ReportVo;
import com.himedia.serviceimpl.ChatServiceImpl;
import com.himedia.services.ChatroomService;
import com.himedia.services.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {
	
	private final ReportService reportService;
	
//	POST : /api/report -> 신고 입력
	@PostMapping("")
	public ResponseEntity<?> insertReport(@RequestBody ReportVo reportVo) throws SQLException {
		int result = reportService.insertReport(reportVo);
		if (result == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("신고 입력중 오류가 발생했습니다.");
		}
		return ResponseEntity.ok(reportVo);
	}
	
//	GET : /api/report/count/{id} -> 신고 개수 출력
//	@GetMapping("/count/{boardId}")
//	public ResponseEntity<?> selectReportCount(@PathVariable Integer boardId) {
//		int result = reportService.selectReportCount(boardId);
//		return ResponseEntity.ok(result);
//	}

}
