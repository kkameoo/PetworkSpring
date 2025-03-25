package com.himedia.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.himedia.repository.vo.ChatMessageVo;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
	
//	private final RedisTemplate<String>
	private final RedisPublisher redisPublisher;
	private final RedisTemplate<String, Object> redisTemplate;
	private static final String CHAT_KEY = "chat:room1"; // 채팅방 키
	private static final int MAX_MESSAGES = 50;
	private final ObjectMapper objectMapper;
	


	public void sendMessage(String channel, ChatMessageVo message) throws IOException {
	    redisPublisher.publish(channel, message);
	    saveMessage(message);
	    System.out.println(getRecentMessages());
	}
	
	public void saveMessage(ChatMessageVo message) throws IOException {
		String jsonMessage = objectMapper.writeValueAsString(message);
		System.out.println(jsonMessage);
		redisTemplate.opsForList().leftPush(CHAT_KEY, jsonMessage);
		redisTemplate.opsForList().trim(CHAT_KEY, 0, MAX_MESSAGES - 1);
	} 
	
	public List<Object> getRecentMessages() {
		return redisTemplate.opsForList().range(CHAT_KEY, 0, MAX_MESSAGES - 1);
	}
}
