package com.himedia.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.himedia.services.PhotoService;

@Service
public class PhotoServiceImpl implements PhotoService {
	 @Value("${file.upload-dir}")
	 private String uploadDir;

//	 public String uploadProfilePicture(MultipartFile file, Integer profileId) throws IOException {
//	     String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//	     Path filePath = Paths.get(uploadDir, fileName);
//	     Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//	     PhotoVo photo = new PhotoVo();
//	     photo.setFileName(fileName);
//	     photo.setFilePath(filePath.toString());
//	     photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	     photo.setProfileId(profileId);
//
//	     photoMapper.insertOrUpdatePhoto(photo);  
//
//	     return filePath.toString();
//	 }
}
