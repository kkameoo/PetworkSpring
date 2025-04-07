package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatroomUserVo;
import com.himedia.repository.vo.ChatroomVo;

@Service
public interface ChatroomService {
	// 채팅방 하나 출력
	ChatroomVo selectOneChatroom(Integer id);
	// 채팅방 하나 생성
	int insertChatroom (ChatroomVo chatroomVo);
	// 모든 채팅방 출력
	List<ChatroomVo> selectChatroom();
	// 보드 아이디로 채팅방 하나 출력
	ChatroomVo selectOneChatroomByBoardId(Integer id);
	// 유저 아이디로 채팅방 여러개 출력
	List<ChatroomVo> selectChatroomByUserId(Integer id);
	
}
