package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.ChatroomMapper;
import com.himedia.repository.vo.ChatroomVo;
import com.himedia.services.ChatroomService;

@Service
public class ChatroomServiceImpl implements ChatroomService {
	
	@Autowired
	ChatroomMapper chatroomMapper;
	
	@Override
	public ChatroomVo selectOneChatroom(Integer id) {
		ChatroomVo chatroomVo = chatroomMapper.selectOneChatroom(id);
		return chatroomVo;
	}

	@Override
	public int insertChatroom(ChatroomVo chatroomVo) {
		int result = chatroomMapper.insertChatroom(chatroomVo);
		return result;
	}

	@Override
	public List<ChatroomVo> selectChatroom() {
		List<ChatroomVo> chatroomVos = chatroomMapper.selectChatroom();
		return chatroomVos;
	}

}
