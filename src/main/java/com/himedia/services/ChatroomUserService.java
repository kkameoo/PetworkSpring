package com.himedia.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatroomUserVo;

@Service
public interface ChatroomUserService {
	// 채팅방 유저 입력
	int insertChatroomUser(ChatroomUserVo chatroomUserVo);
	
	// 채팅유저 체크후 없으면 유저 입력
	int checkAndInsertChatroomUser(ChatroomUserVo chatroomUserVo);
	
	// 채팅방 id로 유저들 검색
	List<ChatroomUserVo> selectChatroomUsersByRoomId(Integer id);
}
