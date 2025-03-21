package com.himedia.serviceimpl;

import java.io.File;
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
			
			   String emailContent = "<div style='text-align: center; font-family: Arial, sans-serif;'>"
		                + "<img src='cid:logo' style='width:200px; margin-bottom: 20px;'>"
		                + "<h2 style='color: #333;'>페트워크에 오신 것을 환영합니다!</h2>"
		                + "<p style='font-size:18px; color: #555;'>아래 인증 코드를 입력하여 이메일을 인증하세요.</p>"
		                + "<p style='font-size:22px; font-weight: bold; color: blue;'>" + verificationCode + "</p>"
		                + "<p>'본 메일은 인증확인용으로 회신하셔도 답변을 받을 수 없습니다.!</p>"
		                + "<p>문의사항은 카카오톡 문의를 이용해주시기 바랍니다'</p>"		                + "<p>감사합니다!</p>"
		                + "</div>";

		        helper.setText(emailContent, true);  // HTML 지원 가능
		        
		        File file = new File("src/main/resources/static/logo.png");
		        helper.addInline("logo", file);
		
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
