package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ChatroomUserVo;

@Mapper
public interface ChatroomUserMapper {
//	<insert id="insertChatroomUser" parameterType="ChatroomUserVo">
	int insertChatroomUser(ChatroomUserVo chatroomUserVo);
}
