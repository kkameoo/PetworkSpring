package com.himedia.serviceimpl;

import java.io.Console;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.himedia.mappers.ChatroomUserMapper;
import com.himedia.mappers.NotificationMapper;
import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.ChatroomUserVo;
import com.himedia.repository.vo.ChatroomVo;
import com.himedia.repository.vo.NotificationVo;
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
	private final ChatroomUserMapper chatroomUserMapper;
	private final ChatroomMapper chatroomMapper;
	private final SimpMessagingTemplate messagingTemplate;
	private final NotificationMapper notificationMapper;
	// 방 키
	private static final String ROOM_KEY_PREFIX = "room:";
	// 접속중인 유저 키
	private static final String USER_KEY_PREFIX = "room/userlist:";

	@Override
	public void sendMessage(String channel, ChatMessageVo message) throws IOException {
		NotificationVo notificationVo = NotificationVo.builder()
				.chatroomId(message.getChatroomId())
				.content(message.getSender() + "님의 메세지: " + message.getContent())
				.isRead(false)
				.createdAt(Timestamp.valueOf(LocalDateTime.now()))
				.build();
		int result = notificationMapper.insertNotification(notificationVo);
		List<ChatroomUserVo> chatroomUserVos = chatroomUserMapper.selectChatroomUsersByRoomId(message.getChatroomId());
		for (ChatroomUserVo chatroomUserVo : chatroomUserVos) {
			System.out.println(chatroomUserVo.getUserId() + "가나다");
			messagingTemplate.convertAndSend("/user/" + chatroomUserVo.getUserId() + "/notification", notificationVo);
		}
//		messagingTemplate.convertAndSend("/user/" + message.getChatroomId() + "/notification", notificationVo);
	    redisPublisher.publish(channel, message);
	    saveMessage(message, message.getChatroomId());
//	    System.out.println(getRecentMessages());
	}
	
	@Override
	public List<Object> sendUserList(ChatroomUserVo chatroomUserVo) throws IOException {
		System.out.println("받아온 데이터 : " + chatroomUserVo.getUserId() + chatroomUserVo.getChatroomId());
		String chatroomUserKey = USER_KEY_PREFIX + chatroomUserVo.getChatroomId();
		String jsonMessage = objectMapper.writeValueAsString(chatroomUserVo); 
		
		redisTemplate.opsForList().leftPush(chatroomUserKey, jsonMessage);
//		List<Object> userList = new ArrayList<Object>();
//		userList.addAll(redisTemplate.opsForList().range(chatroomUserKey, 0, -1));
		return redisTemplate.opsForList().range(chatroomUserKey, 0, -1);	// 현재 접속중인 유저 목록 반환
	}
	
	@Override
	public List<Object> popUserList(ChatroomUserVo chatroomUserVo) throws IOException {
		String chatroomUserKey = USER_KEY_PREFIX + chatroomUserVo.getChatroomId();
		String jsonMessage = objectMapper.writeValueAsString(chatroomUserVo); 
		
		redisTemplate.opsForList().remove(chatroomUserKey, 1, jsonMessage);
//		List<Object> userList = new ArrayList<Object>();
//		userList.addAll(redisTemplate.opsForList().range(chatroomUserKey, 0, -1));
//		System.out.println("나가기 발동");
		return redisTemplate.opsForList().range(chatroomUserKey, 0, -1);	// 현재 접속중인 유저 목록 반환
		
	}
	
	
	@Override
	public void saveMessage(ChatMessageVo message, Integer chatroomKey) throws IOException {
		System.out.println(message);
		String jsonMessage = objectMapper.writeValueAsString(message); 
		messageBuffer.add(message);
		// 채팅저장
		redisTemplate.opsForList().leftPush(ROOM_KEY_PREFIX + chatroomKey, jsonMessage);
		// 50개 까지 유지
		redisTemplate.opsForList().trim(ROOM_KEY_PREFIX + chatroomKey, 0, MAX_MESSAGES - 1);
		
		// 버퍼에 10개 차면 저장
		if (messageBuffer.size() >= BATCH_SIZE ) {
			insertChatMessages();
		}
	} 
	
	@Override
	public List<Object> getRecentMessages(Integer chatroomKey) {
		ChatroomVo chatroomVo = chatroomMapper.selectOneChatroomByBoardId(chatroomKey);
		return redisTemplate.opsForList().range(ROOM_KEY_PREFIX + chatroomVo.getChatroomId(), 0, MAX_MESSAGES - 1);
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

	@Override
	public int updateNotificationIsRead(Integer id) {
		int result = notificationMapper.updateNotificationIsRead(id);
		return result;
	}

	@Override
	public int deleteNotification(Integer id) {
		int result = notificationMapper.deleteNotification(id);
		return result;
	}
}
