package com.himedia.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.himedia.mappers.PetMapper;
import com.himedia.repository.vo.PetVo;
import com.himedia.services.PetService;

@Service
public class PetServiceImpl implements PetService {

	@Autowired
	private PetMapper petMapper;
	
	 // 펫 등록
    @Override
    public void insertPet(PetVo pet) {
        petMapper.insertPet(pet);
    }

    // 펫 전체 조회 (userId 기준)
    @Override
    public List<PetVo> selectPetsByUserId(int userId) {
        return petMapper.selectPetsByUserId(userId);
    }

    // 펫 단일 조회
    @Override
    public PetVo selectPetById(int petId) {
        return petMapper.selectPetById(petId);
    }

    // 펫 수정
    @Override
    public void updatePet(PetVo pet) {
        petMapper.updatePet(pet);
    }

    // 펫 삭제
    @Override
    public void deletePet(int petId) {
        petMapper.deletePet(petId);
    }
}
