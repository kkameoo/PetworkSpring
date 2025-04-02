package com.himedia.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.mappers.BoardPhotoMapper;
import com.himedia.mappers.PetPhotoMapper;
import com.himedia.repository.vo.BoardPhotoVo;
import com.himedia.repository.vo.FileVo;
import com.himedia.repository.vo.PetPhotoVo;
import com.himedia.services.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private BoardPhotoMapper boardPhotoMapper;
	@Autowired
	private PetPhotoMapper petPhotoMapper;
	
	 @Value("${file.upload-dir}")
	 private String uploadDir;
	 
	 public FileVo uploadPicture(MultipartFile file) throws IOException {
	     String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	     Path filePath = Paths.get(uploadDir, fileName);
	     Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	     FileVo fileVo = new FileVo(fileName, filePath);
	     return fileVo;
	 }
	 
	 @Override
	 public String uploadBoardPicture(MultipartFile file, Integer id) throws IOException {
		 FileVo fileVo = uploadPicture(file);
	     BoardPhotoVo boardPhotoVo = new BoardPhotoVo();
	     boardPhotoVo.setBoardPhotoName(fileVo.getFileName());
	     boardPhotoVo.setBoardPhotoSrc(fileVo.getFilePath().toString());
	     boardPhotoVo.setBoardId(id);
	     boardPhotoMapper.insertOrUpdateBoardPhoto(boardPhotoVo);  

	     return fileVo.getFilePath().toString();
	 }
	 
	 @Override
	 public String uploadPetPicture(MultipartFile file, Integer id) throws IOException {
		 FileVo fileVo = uploadPicture(file);
	     PetPhotoVo petPhotoVo = new PetPhotoVo();
	     petPhotoVo.setPetPhotoName(fileVo.getFileName());
	     petPhotoVo.setPetPhotoSrc(fileVo.getFilePath().toString());
	     petPhotoVo.setPetId(id);
	     petPhotoMapper.insertOrUpdatePetPhoto(petPhotoVo);  
	     return fileVo.getFilePath().toString();
	 }

	@Override
	public List<BoardPhotoVo> getBoardPhoto(Integer id) {
		List<BoardPhotoVo> boardPhotoVos = boardPhotoMapper.getBoardPhotoById(id);
		return boardPhotoVos;
	}
	
	@Override
	public PetPhotoVo getPetPhoto(Integer id) {
		PetPhotoVo petPhotoVo = petPhotoMapper.getPetPhotoById(id);
		return petPhotoVo;
	}

	@Override
	public Resource convertFile(String fileSrc) throws IOException {
		Path imagePath = Paths.get(fileSrc);
		Resource resource = new UrlResource(imagePath.toUri());
		return resource;
	}
}
