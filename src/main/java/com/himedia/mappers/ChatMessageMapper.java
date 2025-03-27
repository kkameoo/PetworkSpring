package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ChatMessageVo;

@Mapper
public interface ChatMessageMapper {
//	<select id="selectAllChatMessage" parameterType="int" resultType="ChatMessageVo">
	List<ChatMessageVo> selectAllChatMessage(Integer roomId);
	
//	<insert id="insertChatMessages">
	void insertChatMessages(List<ChatMessageVo> messageVos);
	
//	<select id="selectAllChatMessageByBoardId" parameterType="int" resultType="ChatMessageVo">
	List<ChatMessageVo> selectAllChatMessageByBoardId(Integer Id);
}
