package com.himedia.services;

import java.util.List;

import com.himedia.repository.vo.PetVo;

public interface PetService {

	void insertPet(PetVo pet);  // 펫 등록
	List<PetVo> selectPetsByUserId(int userId);	// 펫 전체 조회
    PetVo selectPetById(int petId);	// 펫 단일 조회
    void updatePet(PetVo pet);	// 펫 정보 수정
    void deletePet(int petId);	// 펫 삭제
}
