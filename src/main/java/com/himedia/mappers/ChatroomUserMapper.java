package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ChatroomUserVo;

@Mapper
public interface ChatroomUserMapper {
//	<insert id="insertChatroomUser" parameterType="ChatroomUserVo">
	int insertChatroomUser(ChatroomUserVo chatroomUserVo);
	
//	<select id="selectChatroomUsers" resultType="ChatroomUserVo">
	List<ChatroomUserVo> selectChatroomUsers();
	
//	<select id="selectChatroomUsersByRoomId" parameterType="int" resultType="ChatroomUserVo">
	List<ChatroomUserVo> selectChatroomUsersByRoomId(Integer id);
}
