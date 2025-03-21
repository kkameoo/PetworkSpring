package com.himedia.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.repository.vo.BoardPhotoVo;

@Service
public interface PhotoService {
	// 사진 업로드
	String uploadProfilePicture(MultipartFile file, Integer id) throws IOException;
	// 사진 조회
	BoardPhotoVo getBoardPhoto(Integer id);
}
