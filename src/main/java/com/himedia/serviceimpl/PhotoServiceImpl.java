package com.himedia.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.mappers.BoardPhotoMapper;
import com.himedia.repository.vo.BoardPhotoVo;
import com.himedia.services.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private BoardPhotoMapper boardPhotoMapper;
	
	 @Value("${file.upload-dir}")
	 private String uploadDir;

	 @Override
	 public String uploadProfilePicture(MultipartFile file, Integer id) throws IOException {
	     String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	     Path filePath = Paths.get(uploadDir, fileName);
	     Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	     BoardPhotoVo boardPhotoVo = new BoardPhotoVo();
	     boardPhotoVo.setBoardPhotoName(fileName);
	     boardPhotoVo.setBoardPhotoSrc(filePath.toString());
	     boardPhotoVo.setBoardId(id);
	     System.out.println(boardPhotoVo);
	     boardPhotoMapper.insertOrUpdateBoardPhoto(boardPhotoVo);  

	     return filePath.toString();
	 }

	@Override
	public BoardPhotoVo getBoardPhoto(Integer id) {
		BoardPhotoVo boardPhotoVo = boardPhotoMapper.getBoardPhotoById(id);
		return boardPhotoVo;
	}

	@Override
	public Resource convertFile(String fileSrc) throws IOException {
		Path imagePath = Paths.get(fileSrc);
		Resource resource = new UrlResource(imagePath.toUri());
		return resource;
	}
}
