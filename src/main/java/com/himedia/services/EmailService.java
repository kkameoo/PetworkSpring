package com.himedia.services;

public interface EmailService {
	// 이메일 전송 메서드
	void sendVerificationEmail(String email);
	
	// 인증 코드 검증
	boolean verifyCode(String email, String code);
}
