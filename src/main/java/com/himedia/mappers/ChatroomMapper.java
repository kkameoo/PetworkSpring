package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.ChatroomVo;

@Mapper
public interface ChatroomMapper {
//	<select id="selectOneChatroom" parameterType="int" resultType="ChatroomVo">
	ChatroomVo selectOneChatroom(Integer id);
}
