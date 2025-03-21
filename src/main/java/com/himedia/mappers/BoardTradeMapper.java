package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardWalkVo;

@Mapper
public interface BoardTradeMapper {
	//	<insert id="insertBoardTrade" parameterType="boardTradeVo">
	int insertBoardTrade(BoardTradeVo boardTradeVo);
}

