package com.himedia.services;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.BoardPhotoVo;

@Service
public interface PhotoService {
	// 보드 사진 업로드
	String uploadProfilePicture(MultipartFile file, Integer id) throws IOException;
	// 보드 사진 조회
	BoardPhotoVo getBoardPhoto(Integer id);
	// 사진 변환
	Resource convertFile(String fileName) throws IOException;
}
