package com.himedia.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.himedia.repository.vo.PetVo;

@Mapper
public interface PetMapper {
	
	void insertPet(PetVo pet); // CREATE
	List<PetVo> selectPetsByUserId(Integer userId);  // READ 전체
    PetVo selectPetById(Integer petId);	// READ 단일
    void updatePet(PetVo pet);	// UPDATE
    void deletePet(Integer petId); 	// DELETE
}
