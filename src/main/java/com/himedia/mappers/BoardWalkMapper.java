package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardWalkVo;

@Mapper
public interface BoardWalkMapper {
	//	<insert id="insertBoardWalk" parameterType="BoardWalkVo">
	int insertBoardWalk(BoardWalkVo boardWalkVo);
}
