package com.himedia.services;

import org.springframework.stereotype.Service;

import com.himedia.repository.vo.ChatroomVo;

@Service
public interface ChatroomService {
	
	ChatroomVo selectOneChatroom(Integer id);
}
