package com.himedia.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.BoardPhotoVo;
import com.himedia.repository.vo.PetPhotoVo;

@Service
public interface PhotoService {
	// 보드 사진 업로드
	String uploadBoardPicture(MultipartFile file, Integer id) throws IOException;
	// 보드 사진 조회
	List<BoardPhotoVo> getBoardPhoto(Integer id);
	// 펫 사진 업로드
	String uploadPetPicture(MultipartFile file, Integer id) throws IOException;
	// 펫 사진 조회
	PetPhotoVo getPetPhoto(Integer id);
	// 사진 변환
	Resource convertFile(String fileName) throws IOException;
}
