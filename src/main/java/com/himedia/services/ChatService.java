package com.himedia.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatMessageVo;

@Service
public interface ChatService {
	// 메세지 전송
	void sendMessage(String channel, ChatMessageVo message) throws IOException;
	// 메세지 저장
	void saveMessage(ChatMessageVo message, Integer chatroomKey) throws IOException;
	// 최근 50개 메시지 가져오기(redis)
	List<Object> getRecentMessages(Integer chatroomKey);
	// 특정 방의 전체 메시지 조회(Mysql)
	List<ChatMessageVo> selectAllChatMessage(Integer roomId);
	
	void insertChatMessages();
//	채팅 가져오기 
//	List<ChatMessageVo> selectAllChatMessageByBoardId(Integer Id);

}
