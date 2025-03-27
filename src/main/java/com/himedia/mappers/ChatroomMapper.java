package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ChatroomVo;

@Mapper
public interface ChatroomMapper {
//	<select id="selectOneChatroom" parameterType="int" resultType="ChatroomVo">
	ChatroomVo selectOneChatroom(Integer id);
	
//	<insert id="insertChatroom" parameterType="ChatroomVo">
	int insertChatroom (ChatroomVo chatroomVo);
	
//	<select id="selectChatroom" resultType="ChatroomVo">
	List<ChatroomVo> selectChatroom();

//	<select id="selectOneChatroomByBoardId" parameterType="int" resultType="ChatroomVo">
	ChatroomVo selectOneChatroomByBoardId(Integer id);
}
