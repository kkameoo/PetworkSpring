package com.himedia.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.PetPhotoVo;

@Mapper
public interface PetPhotoMapper {
//	<insert id="insertOrUpdatePetPhoto" parameterType="PetPhotoVo">
	int insertOrUpdatePetPhoto(PetPhotoVo petPhotoVo);
//	<select id="getPetPhotoById" parameterType="int" resultType="PetPhotoVo">
	PetPhotoVo getPetPhotoById(Integer id);
}
