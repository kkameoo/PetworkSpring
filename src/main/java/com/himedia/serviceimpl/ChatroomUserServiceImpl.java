package com.himedia.serviceimpl;

import org.springframework.stereotype.Service;

import com.himedia.mappers.ChatroomUserMapper;
import com.himedia.repository.vo.ChatroomUserVo;
import com.himedia.services.ChatroomUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatroomUserServiceImpl implements ChatroomUserService{

	private final ChatroomUserMapper chatroomUserMapper;
	
	@Override
	public int insertChatroomUser(ChatroomUserVo chatroomUserVo) {
		int result = chatroomUserMapper.insertChatroomUser(chatroomUserVo);
		return result;
	}

}
