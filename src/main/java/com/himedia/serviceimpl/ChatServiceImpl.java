package com.himedia.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.himedia.mappers.ChatMessageMapper;
import com.himedia.mappers.ChatroomMapper;
import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.ChatroomVo;
import com.himedia.services.ChatService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
	
	private final RedisPublisher redisPublisher;
	private final RedisTemplate<String, Object> redisTemplate;
	// 채팅들을 저장시키는 메세지 버퍼
	private final List<ChatMessageVo> messageBuffer = new ArrayList<>();
	// 레디스에 저장되는 최대 메세지 50개
	private static final int MAX_MESSAGES = 50;
	// 메세지 버퍼에 저장되는 최대 개수 10개
	private final int BATCH_SIZE = 10;
	private final ObjectMapper objectMapper;
	private final ChatMessageMapper chatMessageMapper;
	private final ChatroomMapper chatroomMapper;

	@Override
	public void sendMessage(String channel, ChatMessageVo message) throws IOException {
	    redisPublisher.publish(channel, message);
	    saveMessage(message, message.getChatroomId().toString());
//	    System.out.println(getRecentMessages());
	}
	
	@Override
	public void saveMessage(ChatMessageVo message, String chatroomKey) throws IOException {
		String jsonMessage = objectMapper.writeValueAsString(message); 
		messageBuffer.add(message);
		// 채팅저장
		redisTemplate.opsForList().leftPush(chatroomKey, jsonMessage);
		// 50개 까지 유지
		redisTemplate.opsForList().trim(chatroomKey, 0, MAX_MESSAGES - 1);
		
		// 버퍼에 10개 차면 저장
		if (messageBuffer.size() >= BATCH_SIZE ) {
			insertChatMessages();
		}
	} 
	
	@Override
	public List<Object> getRecentMessages(Integer chatroomKey) {
		ChatroomVo chatroomVo = chatroomMapper.selectOneChatroomByBoardId(chatroomKey);
		return redisTemplate.opsForList().range(chatroomVo.getChatroomId().toString(), 0, MAX_MESSAGES - 1);
	}

	@Override
	public List<ChatMessageVo> selectAllChatMessage(Integer roomId) {
		List<ChatMessageVo> chatMessageVos = chatMessageMapper.selectAllChatMessage(roomId);
		return chatMessageVos;
	}

	
	// 10초 마다 강제 저장
	@Scheduled(fixedRate = 10000)	
	public synchronized void insertChatMessages() {
		System.out.println("Save Message Buffer");
		if (!messageBuffer.isEmpty()) {
		chatMessageMapper.insertChatMessages(messageBuffer);
		}
		messageBuffer.clear();
	}
	
	// 서버 종료 시 강제 저장
	@PreDestroy  
    public void onShutdown() {
        System.out.println("서버 종료 중, 메시지 강제 저장");
        insertChatMessages();
    }
}
