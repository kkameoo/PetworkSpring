package com.himedia.serviceimpl;

import java.util.Iterator;
import java.util.List;

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
	
	@Override
	public int checkAndInsertChatroomUser(ChatroomUserVo chatroomUserVo) {
		List<ChatroomUserVo> chatroomUserVos = chatroomUserMapper.selectChatroomUsersByRoomId(chatroomUserVo.getChatroomId());
		if (chatroomUserVos.isEmpty()) {
			int result = chatroomUserMapper.insertChatroomUser(chatroomUserVo);
			return result;
		}
		boolean flag = false;
		for(ChatroomUserVo cUserVo : chatroomUserVos) {
			if (cUserVo.getUserId() == chatroomUserVo.getUserId()) {
				 flag = true;
				 break;
			}
		}
		if (flag == false) {
//			System.out.println("존재안함");
			int result = chatroomUserMapper.insertChatroomUser(chatroomUserVo);
			return result;
		}
		return 0;
	}

	@Override
	public List<ChatroomUserVo> selectChatroomUsersByRoomId(Integer id) {
		List<ChatroomUserVo> chatroomUserVos = chatroomUserMapper.selectChatroomUsersByRoomId(id);
		return chatroomUserVos;
	}

}
