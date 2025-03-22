package com.himedia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himedia.repository.vo.PetVo;
import com.himedia.repository.vo.UserVo;
import com.himedia.services.PetService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	 // 펫 등록 (CREATE)
    @PostMapping("/create")
    public ResponseEntity<String> createPet(@RequestBody PetVo pet, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        pet.setUserId(sessionUser.getUserId());
        petService.insertPet(pet);
        return ResponseEntity.ok("펫 등록 성공");
    }

    // 특정 사용자 펫 전체 조회 (READ)
    @GetMapping("/list")
    public ResponseEntity<List<PetVo>> getPetsByUser(HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(401).build();
        }
        List<PetVo> pets = petService.selectPetsByUserId(sessionUser.getUserId());
        return ResponseEntity.ok(pets);
    }

    //  펫 단일 조회 (READ)
    @GetMapping("/{petId}")
    public ResponseEntity<PetVo> getPetById(@PathVariable int petId) {
        PetVo pet = petService.selectPetById(petId);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    // 펫 정보 수정 (UPDATE)
    @PutMapping("/update")
    public ResponseEntity<String> updatePet(@RequestBody PetVo pet, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        pet.setUserId(sessionUser.getUserId());
        petService.updatePet(pet);
        return ResponseEntity.ok("펫 정보 수정 완료");
    }

    // 펫 삭제 (DELETE)
    @DeleteMapping("/delete/{petId}")
    public ResponseEntity<String> deletePet(@PathVariable int petId, HttpSession session) {
        UserVo sessionUser = (UserVo) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        petService.deletePet(petId);
        return ResponseEntity.ok("펫 삭제 완료");
    }
}
