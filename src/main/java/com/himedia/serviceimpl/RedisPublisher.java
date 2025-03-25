package com.himedia.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.himedia.repository.vo.ChatMessageVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisPublisher {

	private final RedisTemplate<String, Object> redisTemplate;

	private final ObjectMapper objectMapper;
	
	public void publish(String topic, ChatMessageVo message) {
		try {
			System.out.println("topic : "+ topic + "message : "+ message);
			String jsonMessage = objectMapper.writeValueAsString(message);
			redisTemplate.convertAndSend(topic, jsonMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
