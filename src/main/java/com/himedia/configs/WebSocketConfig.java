package com.himedia.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	 @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/ws")
	                .setAllowedOriginPatterns("*")
	                .withSockJS();
	    }

	    @Override
	    public void configureMessageBroker(MessageBrokerRegistry registry) {
	    	// 브로커의 목적지 /topic으로 지정 + /user 추가
	        registry.enableSimpleBroker("/topic", "user");
	        // 메세지를 보낼 시 /app으로 전달
	        registry.setApplicationDestinationPrefixes("/app");
	        // 메시지 알람 받기
	        registry.setUserDestinationPrefix("/user");
	    }
}
