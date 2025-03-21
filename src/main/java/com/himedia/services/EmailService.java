package com.himedia.services;

public interface EmailService {
	// 회원가입 이메일 이메일 전송
	void sendVerificationEmail(String email);
	
	// 비밀번호 재설정 이메일 전송
	void sendPasswordResetEmail(String email);
	
	// 인증 코드 검증
	boolean verifyCode(String email, String code);
}
