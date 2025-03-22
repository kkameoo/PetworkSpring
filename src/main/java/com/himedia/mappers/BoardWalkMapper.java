package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardWalkVo;

@Mapper
public interface BoardWalkMapper {
	//	<insert id="insertBoardWalk" parameterType="BoardWalkVo">
	int insertBoardWalk(BoardWalkVo boardWalkVo);
	// <select id="selectAllBoardWalk" resultType="BoardWalkVo">
	List<BoardWalkVo> selectAllBoardWalk();
	// <select id="selectBoardWalk" resultType="BoardWalkVo" parameterType="int">
	BoardWalkVo selectBoardWalk(Integer id);
	//	<update id="updateBoardWalk" parameterType="BoardWalkVo">
	int updateBoardWalk(BoardWalkVo boardWalkVo);
	

}
