package com.himedia.services;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatroomUserVo;

@Service
public interface ChatroomUserService {
	// 채팅방 유저 입력
	int insertChatroomUser(ChatroomUserVo chatroomUserVo);
}
