package com.himedia.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.himedia.services.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	// 인증 코드 저장 (HashMap)
	private Map<String, String> verificationCodes = new HashMap<>();
	
	// 램덤 인증 코드 생성
	private String generateVerificationCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000); // 6자리 랜덤생성
		return String.valueOf(code);
	}
	
	@Override
	public void sendVerificationEmail(String email) {
		String verificationCode = generateVerificationCode();
		verificationCodes.put(email, verificationCode);	// 이메일 코드 저장
		
		try {
			// 이메일 메시지 생성
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setTo(email);
			helper.setSubject("이메일 인증코드");
			helper.setText("인증 코드:" + verificationCode, true);  // HTML 지원 가능
		
			mailSender.send(message);	// 메일 전송
		} catch (Exception e) {
			throw new RuntimeException("이메일 전송 실패:" + e.getMessage());
		}
	}
	
	@Override
	public boolean verifyCode(String email, String code) {
		// 저장된 인증 코드와 비교
		return verificationCodes.containsKey(email) && verificationCodes.get(email).equals(code);
	}
}
