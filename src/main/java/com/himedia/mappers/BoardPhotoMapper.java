package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.himedia.repository.vo.BoardPhotoVo;



@Mapper
public interface BoardPhotoMapper {
//	<insert id="insertOrUpdateBoardPhoto" parameterType="BoardPhotoVo">
	int insertOrUpdateBoardPhoto(BoardPhotoVo boardPhotoVo);
	
	List<BoardPhotoVo> getBoardPhotoById (Integer id);
	
	int insertBoardPhotos(List<BoardPhotoVo> boardPhotoVos);
	
	int deleteBoardPhotos(@Param("ids") List<Integer> deleted);
}
