package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.BoardPhotoVo;

@Mapper
public interface BoardPhotoMapper {
//	<insert id="insertOrUpdateBoardPhoto" parameterType="BoardPhotoVo">
	int insertOrUpdateBoardPhoto(BoardPhotoVo boardPhotoVo);
	
	BoardPhotoVo getBoardPhotoById (Integer id);

}
