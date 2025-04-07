package com.himedia.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.services.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	// 회원가입 이메일 인증 코드 전송
	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
		}
		emailService.sendVerificationEmail(email);
		return ResponseEntity.ok("이메일 전송 완료");
	}
	
	// 비밀번호 재설정 이메일 인증 코드 전송
	@PostMapping("/send-reset-code")
	public ResponseEntity<String> sendPasswordResetEmail(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		
		if (email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body("이메일을 입력해주세요.");
		}
		
		emailService.sendPasswordResetEmail(email);
		return ResponseEntity.ok("비밀번호 재설정 이메일 전송 완료");
	}
	
	
	// 이메일 인증 코드 확인
	@PostMapping("/verify")
	public ResponseEntity<String> verifyCode(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		String code = request.get("code");
		
		if (email == null || email.isEmpty() || code == null || code.isEmpty()) {
			return ResponseEntity.badRequest().body("이메일과 인증코드를 입력해주세요.");
		}
		
		boolean isValid = emailService.verifyCode(email, code);
		return isValid ? ResponseEntity.ok("이메일 인증 성공!")
					   : ResponseEntity.badRequest().body("인증 코드가 올바르지 않습니다.");
	}
}
