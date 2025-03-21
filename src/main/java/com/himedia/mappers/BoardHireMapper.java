package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardHireVo;


@Mapper
public interface BoardHireMapper {
	//	<insert id="insertBoardHire" parameterType="boardHireVo">
	int insertBoardHire(BoardHireVo boardHireVo);
}
