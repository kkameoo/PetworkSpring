package com.himedia.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.himedia.handlers.JwtFilter;

@Configuration
public class JwtFilterConfig {
	 @Bean
	 public FilterRegistrationBean<JwtFilter> jwtFilterRegistration(JwtFilter jwtFilter) {
	     FilterRegistrationBean<JwtFilter> registration = new FilterRegistrationBean<>();
	     registration.setFilter(jwtFilter);
	     registration.addUrlPatterns("/mypage"); // 보호할 경로
	     return registration;
	 }

}
