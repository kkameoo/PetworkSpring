package com.himedia.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatMessageVo;
import com.himedia.repository.vo.ChatroomUserVo;

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
	// 유저리스트 레디스에 저장 및 출력
	List<Object> sendUserList(ChatroomUserVo chatroomUserVo) throws IOException;
	// 유저리스트에 제거 및 출력
	List<Object> popUserList(ChatroomUserVo chatroomUserVo) throws IOException;
	
	// 알람 읽음 표시
	int updateNotificationIsRead(Integer id);
	// 알람 삭제
	int deleteNotification(Integer id);

}
