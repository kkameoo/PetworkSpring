package com.himedia.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.BoardPhotoVo;
import com.himedia.repository.vo.PetPhotoVo;
import com.himedia.services.PhotoService;

@RestController
@RequestMapping("/api/photo")
public class PhotoController {
	
	@Autowired
	private PhotoService photoService;

	
//	POST : /api//photo/board/upload -> 프로필 사진 업로드
	@PostMapping("/board/upload")
	public ResponseEntity<String> uploadBoardPicture(@RequestParam("file") MultipartFile file,
			@RequestParam("id") Integer id) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("파일을 선택해주세요.");
		}

		try {
			String filePath = photoService.uploadBoardPicture(file, id);
			return ResponseEntity.ok("게시물 사진이 성공적으로 업로드되었습니다. 파일 경로: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생했습니다.");
		}
	}
	
//	POST : /api//photo/pet/upload -> 프로필 사진 업로드
	@PostMapping("/pet/upload")
	public ResponseEntity<String> uploadPetPicture(@RequestParam("file") MultipartFile file,
			@RequestParam("id") Integer id) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("파일을 선택해주세요.");
		}

		try {
			String filePath = photoService.uploadPetPicture(file, id);
			return ResponseEntity.ok("펫 사진이 성공적으로 업로드되었습니다. 파일 경로: " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생했습니다.");
		}
	}
	
//	GET : /api/photo/board/upload/{id} -> 프로필 사진 조회 (여러 장)
	@GetMapping("/board/upload/{id}")
	public ResponseEntity<?> getBoardPhoto(@PathVariable Integer id) {
		List<BoardPhotoVo> boardPhotoVos = photoService.getBoardPhoto(id);
		
		// 검색된 데이터가 없을 경우 404 반환
	    if (boardPhotoVos == null || boardPhotoVos.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색된 데이터가 존재하지 않습니다.");
	    }
	    
	    // 파일을 Resource 리스트로 변환
	    List<Resource> resources = boardPhotoVos.stream()
	        .map(photo -> {
				try {
					return photoService.convertFile(photo.getBoardPhotoSrc());
				} catch (IOException e) {
					return null;
					
				}
			})
	        .filter(Objects::nonNull) // 변환 실패한 경우 필터링
	        .collect(Collectors.toList());
	    
	 // 변환된 데이터가 없는 경우 404 반환
	    if (resources.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 변환에 실패했습니다.");
	    }
	    
	    try {
	        // Base64 변환
	        List<String> base64Images = resources.stream()
	            .map(resource -> {
	                try {
	                    return Base64.getEncoder().encodeToString(resource.getInputStream().readAllBytes());
	                } catch (IOException e) {
	                    throw new RuntimeException("이미지 변환 실패", e);
	                }
	            })
	            .collect(Collectors.toList());

	        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(base64Images);
	    } catch (Exception e) {
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 처리 중 오류 발생: " + e.getMessage());
	    }
	    
	}
	
//	GET : /api/pet/board/upload/{id} -> 프로필 사진 조회 (한 장)
	@GetMapping("/pet/upload/{id}")
	public ResponseEntity<?> getPetPhoto(@PathVariable Integer id) throws IOException {
		PetPhotoVo petPhotoVo = photoService.getPetPhoto(id);
		
		// 검색된 데이터가 없을 경우 404 반환
	    if (petPhotoVo == null) {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터가 존재하지 않습니다.");
	    }
	    // src데이터를 Reource로 변환
	    Resource resource = photoService.convertFile(petPhotoVo.getPetPhotoSrc());
		// resource 데이터가 null이면 오류 발생
		if (resource == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터 변환에 실패했습니다.");
		}
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG) // 이미지 타입 설정
		        .body(resource);
	}
}
