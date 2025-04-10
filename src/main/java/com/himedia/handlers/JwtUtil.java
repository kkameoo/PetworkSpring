package com.himedia.handlers;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private final String SECRET_KEY = "my-secret-key-which-is-very-long-123456789012345678901234"; // 최소 256bit
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간
    
    public String generateToken(String username) {
    	return Jwts.builder()
    			.setSubject(username)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
    			.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
    			.compact();
    }
    
    public String validateTokenAndGetUsername(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(SECRET_KEY.getBytes())
    			.build()
    			.parseClaimsJws(token)
    			.getBody()
    			.getSubject();
    }
}