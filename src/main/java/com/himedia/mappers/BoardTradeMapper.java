package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardTradeVo;
import com.himedia.repository.vo.BoardWalkVo;

@Mapper
public interface BoardTradeMapper {
	//	<insert id="insertBoardTrade" parameterType="boardTradeVo">
	int insertBoardTrade(BoardTradeVo boardTradeVo);
	// <select id="selectAllBoardTrade" resultType="BoardTradeVo">
	List<BoardTradeVo> selectAllBoardTrade();
	// <select id="selectBoardTrade" resultType="BoardTradeVo" parameterType="int">
	BoardTradeVo selectBoardTrade(Integer id);
}

