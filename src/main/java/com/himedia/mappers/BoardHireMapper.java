package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardHireVo;
import com.himedia.repository.vo.BoardWalkVo;


@Mapper
public interface BoardHireMapper {
	//	<insert id="insertBoardHire" parameterType="boardHireVo">
	int insertBoardHire(BoardHireVo boardHireVo);
	//	<select id="selectAllBoardHire" resultType="BoardHireVo">
	List<BoardHireVo> selectAllBoardHire();
	// <select id="selectBoardHire" resultType="BoardHireVo" parameterType="int">
	BoardHireVo selectBoardHire(Integer id);
	//	<update id="updateBoardHire" parameterType="BoardHireVo">
	int updateBoardHire(BoardHireVo boardHireVo);
	// <select id="selectPopularBoardHire" resultType="BoardHireVo">
	List<BoardHireVo> selectPopularBoardHire();
}
