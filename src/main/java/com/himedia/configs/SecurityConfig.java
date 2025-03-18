package com.himedia.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (테스트 환경)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user/register", "/api/user/**").permitAll() // 회원가입 및 API 접근 허용
                .anyRequest().authenticated() // 그 외 요청은 인증 필요
            )
            .formLogin(form -> form.disable()) // 기본 로그인 폼 비활성화
            .httpBasic(httpBasic -> httpBasic.disable()); // 기본 인증 비활성화

        return http.build();
    }
}
