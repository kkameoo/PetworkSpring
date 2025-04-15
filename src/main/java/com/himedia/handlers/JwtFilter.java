package com.himedia.handlers;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
	           String token = authHeader.substring(7); // "Bearer " 이후만 추출
	           try {
	               // 2. 토큰 검증 및 파싱
	               String username = jwtUtil.validateTokenAndGetUsername(token);

	               // 3. 요청에 사용자 정보 임시 저장
	               request.setAttribute("username", username);

	           } catch (Exception e) {
	               // 4. 검증 실패 → 401 Unauthorized
	               response.setStatus(HttpStatus.UNAUTHORIZED.value());
	               return;
	           }
		}
	}
}